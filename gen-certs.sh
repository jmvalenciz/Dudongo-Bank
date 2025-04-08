#!/bin/bash
set -e

# Load passkeys for keystores
set -a && source .cert_passkeys && set +a

COUNTRY="CO"
STATE="Antioquia"
CITY="Medellin"
ORGANIZATION="DudongoBank"
ORG_UNIT="Backend"

microservices=("clientes" "cuentas")

mkdir -p certs
cd certs

create_cert() {
  local name=$1
  local subj="/C=${COUNTRY}/ST=${STATE}/L=${CITY}/O=${ORGANIZATION}/OU=${ORG_UNIT}/CN=${name}"
  cat > ${name}.cnf <<EOF
[ req ]
default_bits       = 4096
prompt            = no
default_md        = sha256
distinguished_name = req_distinguished_name
req_extensions    = v3_req

[ req_distinguished_name ]
C  = CO
ST = Antioquia
L  = Medellin
O  = DudongoBank
OU = Backend
CN = localhost

[ v3_req ]
subjectAltName = @alt_names

[ alt_names ]
DNS.1 = ${name}
DNS.2 = localhost
IP.1  = 127.0.0.1
EOF

  echo "Key"
  openssl genrsa -out ${name}.key 2048

  echo "CSR"
  openssl req -new -key ${name}.key -out ${name}.csr -subj "${subj}" -config ${name}.cnf

  echo "Signing"
  openssl x509 -req -in ${name}.csr -CA ca.crt -CAkey ca.key -CAcreateserial \
    -out ${name}.crt -days 3650 -sha256 \
    -extensions v3_req -extfile ${name}.cnf

  openssl x509 -req -in ${name}.csr -CA ca.crt -CAkey ca.key -CAcreateserial \
    -out ${name}.crt -days 3650 -sha256

  cat ${name}.key ${name}.crt > ${name}.pem
  #rm -f ${name}.cnf
}

create_keystore() {
  local name=$1
  local pass=$2

  openssl pkcs12 -export \
    -in ${name}.crt \
    -inkey ${name}.key \
    -certfile ca.crt \
    -name ${name} \
    -out ${name}.p12 \
    -passout pass:${pass}

  keytool -importkeystore \
    -deststorepass ${pass} \
    -destkeypass ${pass} \
    -destkeystore ${name}.jks \
    -srckeystore ${name}.p12 \
    -srcstoretype PKCS12 \
    -srcstorepass ${pass} \
    -alias ${name}
}

if [ ! -f ca.key ]; then
  openssl genrsa -out ca.key 4096
fi

if [ ! -f ca.crt ]; then
  openssl req -new -x509 -days 3650 -key ca.key -out ca.crt \
    -subj "/C=${COUNTRY}/ST=${STATE}/L=${CITY}/O=${ORGANIZATION}/OU=${ORG_UNIT}/CN=CA"
fi

echo "rabbitmq"
create_cert "rabbitmq"
keytool -import \
  -file ca.crt \
  -trustcacerts \
  -alias root \
  -keystore truststore.jks \
  -storepass ${rabbitmq_pass} \
  -noprompt


for service in "${microservices[@]}"; do

  echo "Creando el certificado '${service}'"
  pass_name="${service}_pass"
  eval pass=\$$pass_name
  create_cert "${service}"
  create_keystore "${service}" "${pass}"

  echo "Creado"
done
