CREATE TYPE ETipoCredito AS ENUM ('Proyecto', 'Empresa', 'Vivienda');
CREATE TYPE ETipoInversion AS ENUM ('Ahorro Automatico', 'Inversion Dinamica');
CREATE TYPE EStaffRBAC AS ENUM ('ModeradorForos', 'Asistencia', 'Admin', 'Asesor');

CREATE TABLE Clientes (
    ID SERIAL PRIMARY KEY,
    NumeroDocumento VARCHAR(50) UNIQUE NOT NULL,
    TipoDocumento VARCHAR(20) NOT NULL,
    NombreCompleto VARCHAR(255) NOT NULL,
    FechaNacimiento DATE NOT NULL,
    Telefono VARCHAR(20),
    Email VARCHAR(255) UNIQUE NOT NULL,
    FechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    EstadoCuenta VARCHAR(20) NOT NULL
);
CREATE INDEX idx_clientes_fecha_registro ON Clientes(FechaRegistro);

CREATE TABLE Cuentas (
    ID SERIAL PRIMARY KEY,
    NumeroCuenta VARCHAR(50) UNIQUE NOT NULL,
    TipoCuenta VARCHAR(20) NOT NULL,
    FechaApertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ClienteID INT NOT NULL REFERENCES Clientes(ID)
);
CREATE INDEX idx_cuentas_clienteid ON Cuentas(ClienteID);
CREATE INDEX idx_cuentas_fecha_apertura ON Cuentas(FechaApertura);

CREATE TABLE Tarjetas (
    ID SERIAL PRIMARY KEY,
    NumeroTarjeta VARCHAR(16) UNIQUE NOT NULL,
    Pin VARCHAR(10) NOT NULL,
    FechaExpiracion DATE NOT NULL,
    CCV VARCHAR(4) NOT NULL,
    CuentaID INT NOT NULL REFERENCES Cuentas(ID)
);
CREATE INDEX idx_tarjetas_cuentaid ON Tarjetas(CuentaID);

CREATE TABLE Transacciones (
    ID SERIAL PRIMARY KEY,
    CuentaOrigenID INT REFERENCES Cuentas(ID),
    CuentaDestinoID INT REFERENCES Cuentas(ID),
    TipoTransaccion VARCHAR(50) NOT NULL,
    Valor DECIMAL(15,2) NOT NULL,
    Moneda VARCHAR(10) NOT NULL,
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Descripcion TEXT,
    Status VARCHAR(20) NOT NULL,
    Geolocation VARCHAR(100),
    IpTransaccion VARCHAR(45)
);
CREATE INDEX idx_transacciones_cuenta_origen ON Transacciones(CuentaOrigenID);
CREATE INDEX idx_transacciones_cuenta_destino ON Transacciones(CuentaDestinoID);
CREATE INDEX idx_transacciones_timestamp ON Transacciones(Timestamp);

CREATE TABLE Financiamientos (
    ID SERIAL PRIMARY KEY,
    RequisitosMinimos TEXT NOT NULL,
    Descripcion TEXT NOT NULL,
    DocumentacionNecesaria TEXT NOT NULL,
    TasaBaseInteres DECIMAL(5,2) NOT NULL
);

CREATE TABLE ClientesInversiones (
    ClienteID INT NOT NULL REFERENCES Clientes(ID),
    InversionID INT NOT NULL REFERENCES Inversiones(ID),
    FechaInicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    MontoAprobado DECIMAL(15,2) NOT NULL,
    TasaInteres DECIMAL(5,2) NOT NULL,
    CuotaMensual DECIMAL(15,2) NOT NULL,
    PRIMARY KEY (ClienteID, InversionID)
);

CREATE TABLE Inversiones (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion TEXT NOT NULL
);

CREATE TABLE Preferencias (
    UniqueID SERIAL PRIMARY KEY,
    ClienteID INT NOT NULL REFERENCES Clientes(ID),
    CategoriasInteres TEXT NOT NULL,
    PerfilRiesgo TEXT NOT NULL,
    MetasFinancieras TEXT NOT NULL,
    PlazoTiempo VARCHAR(50) NOT NULL
);
CREATE INDEX idx_preferencias_clienteid ON Preferencias(ClienteID);

CREATE TABLE HistorialCredito (
    ID SERIAL PRIMARY KEY,
    ClienteID INT NOT NULL REFERENCES Clientes(ID),
    PuntajeCredito INT NOT NULL,
    HistorialPagos TEXT NOT NULL,
    DeudasActuales TEXT NOT NULL,
    CreditosActivos TEXT NOT NULL,
    FechaUltimaActualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_historialcredito_clienteid ON HistorialCredito(ClienteID);
CREATE INDEX idx_historialcredito_fecha_actualizacion ON HistorialCredito(FechaUltimaActualizacion);

CREATE TABLE Staff (
    ID SERIAL PRIMARY KEY,
    NombreCompleto VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Telefono VARCHAR(20),
    RBAC EStaffRBAC NOT NULL
);

CREATE TABLE Foros (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Descripcion TEXT NOT NULL,
    CategoriaForo VARCHAR(100) NOT NULL,
    FechaCreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_foros_categoria ON Foros(CategoriaForo);
CREATE INDEX idx_foros_fecha_creacion ON Foros(FechaCreacion);

CREATE TABLE MensajesForos (
    ID SERIAL PRIMARY KEY,
    Contenido TEXT NOT NULL,
    Likes INT DEFAULT 0,
    Respuestas INT DEFAULT 0
);

CREATE TABLE Asesorias (
    UniqueID SERIAL PRIMARY KEY,
    ClienteID INT NOT NULL REFERENCES Clientes(ID),
    AsesorID INT NOT NULL REFERENCES Staff(ID),
    EstadoAsesoria VARCHAR(50) NOT NULL
);
CREATE INDEX idx_asesorias_clienteid ON Asesorias(ClienteID);
CREATE INDEX idx_asesorias_asesorid ON Asesorias(AsesorID);

CREATE TABLE MensajesAsesorias (
    ID SERIAL PRIMARY KEY,
    AsesoriaID INT NOT NULL REFERENCES Asesorias(UniqueID),
    Contenido TEXT NOT NULL,
    FechaEnvio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Emisor VARCHAR(50) NOT NULL
);
CREATE INDEX idx_mensajesasesorias_asesoriaid ON MensajesAsesorias(AsesoriaID);
CREATE INDEX idx_mensajesasesorias_fecha_envio ON MensajesAsesorias(FechaEnvio);

CREATE TABLE CuentasBeneficios (
    CuentaID INT NOT NULL REFERENCES Cuentas(ID),
    BeneficioID INT NOT NULL REFERENCES Beneficios(ID),
    FechaInicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Status VARCHAR(50) NOT NULL,
    PRIMARY KEY (CuentaID, BeneficioID)
);

CREATE TABLE Beneficios (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Descripcion TEXT NOT NULL,
    PorcentajeCashback DECIMAL(5,2) NOT NULL,
    Requisitos TEXT NOT NULL
);

CREATE TABLE DispositivosNotificaciones (
    UniqueID SERIAL PRIMARY KEY,
    TipoDispositivo VARCHAR(50) NOT NULL,
    Token VARCHAR(255) NOT NULL,
    Estado VARCHAR(20) NOT NULL,
    ClienteID INT NOT NULL REFERENCES Clientes(ID)
);
CREATE INDEX idx_dispositivos_clienteid ON DispositivosNotificaciones(ClienteID);

CREATE TABLE NotificacionesClientes (
    ID SERIAL PRIMARY KEY,
    ClienteID INT NOT NULL REFERENCES Clientes(ID),
    DispositivoID INT NOT NULL REFERENCES DispositivosNotificaciones(UniqueID),
    Contenido TEXT NOT NULL,
    FechaEnvio TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_notificaciones_clienteid ON NotificacionesClientes(ClienteID);
CREATE INDEX idx_notificaciones_dispositivoid ON NotificacionesClientes(DispositivoID);
CREATE INDEX idx_notificaciones_fecha_envio ON NotificacionesClientes(FechaEnvio);

