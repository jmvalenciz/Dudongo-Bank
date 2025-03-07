# **Dudongo Bank \- REQUISITOS**

**Version 0.1**
[Juan Manuel Valencia Zapata](mailto:roumin1999@gmail.com)
Feb 14, 2025

## **Descripción**

En este documento, vamos a listar los requisitos de Dudongo Bank, a través del comportamiento del sistema, usando la notación Gherkin para describir la forma en la que este se usaría por los diferentes actores del sistema.

Como parte del contexto del problema, debemos tener en cuenta que *Dudongo Bank,* es una fintech que busca ofrecer al usuario final servicios de crédito, inversión, ahorro, inversión, etc. con productos y servicios a la medida, aprovechando diferentes capacidades tecnológicas modernas como lo son *Openfinance* y también, como podemos inferir, la facilidad que tiene el usuario final de acceder a servicios digitales.

## **Requisitos**

### **Como persona natural, quiero poder crear una cuenta**

**Característica:** Como persona natural, quiero poder crear una cuenta
**Escenario:** La persona no tiene una cuenta a su nombre
&ensp;&ensp;&ensp;&ensp;**Dada** una persona con un historial crediticio bueno
&ensp;&ensp;&ensp;&ensp;**Y** un documento de identidad válido y vigente
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite crear una cuenta
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe crearle una cuenta
&ensp;&ensp;&ensp;&ensp;**Y** solicitarle sus preferencias financieras
&ensp;&ensp;&ensp;&ensp;**Y** crearle un producto de cuenta débito a su nombre
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica:** Como persona natural, quiero poder crear una cuenta
**Escenario:** La persona ya tiene una cuenta a su nombr
&ensp;&ensp;&ensp;&ensp;**Dada** una persona con un historial crediticio buen
&ensp;&ensp;&ensp;&ensp;**Y** un documento de identidad válido y vigente
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite crear una cuenta
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe informarle que ya hay una cuenta asociada a esta persona
&ensp;&ensp;&ensp;&ensp;**Y** el sistema debe finalizar el proceso de creación de cuenta
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 400

**Característica:** Como persona natural, quiero poder crear una cuenta
**Escenario:** La persona no tiene un buen historial crediticio
&ensp;&ensp;&ensp;&ensp;**Dada** una persona con un historial crediticio deficiente
&ensp;&ensp;&ensp;&ensp;**Y** un documento de identidad válido y vigente
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite crear una cuenta
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe informarle que no tiene un historial crediticio lo suficientemente bueno como para crear una cuenta
&ensp;&ensp;&ensp;&ensp;**Y** el sistema debe finalizar el proceso de creación de cuenta
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 400

**Característica:** Como persona natural, quiero poder crear una cuenta
**Escenario:** La persona no tiene un documento de identidad válido
&ensp;&ensp;&ensp;&ensp;**Dada** una persona con un documento de identidad no válido
&ensp;&ensp;&ensp;&ensp;**O** una persona con un documento de identidad no reconocido a nivel nacional
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite crear una cuenta
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe informarle que no cuenta con un documento de identidad válido
&ensp;&ensp;&ensp;&ensp;**Y** el sistema debe finalizar el proceso de creación de cuenta
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 400

### **Como cliente, quiero ver la lista de ingresos y egresos de mi cuenta**

**Característica:** Como cliente, quiero ver la lista de ingresos y egresos de mi cuenta
**Escenario:** El usuario final ha realizado movimientos con su cuenta.
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta con 5 movimientos de ingresos y egresos
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite la lista de movimientos de la cuenta
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe retornar la lista de 5 movimientos de la cuenta con su respectiva fecha y detalles de la transacción
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica:** Como cliente, quiero ver la lista de ingresos y egresos de mi cuenta
**Escenario:** El usuario final no ha realizado gastos con su cuenta.
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta con movimientos de ingresos y egresos con su respectivas fechas
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite la lista de movimientos de la cuenta
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe retornar la lista vacía de movimientos de la cuenta
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

### **Como cliente, quiero poder recibir recomendaciones de uso de mis tarjetas**

**Característica:** Como cliente, quiero poder recibir recomendaciones de uso de mis tarjetas
**Escenario:** El cliente ha realizado movimientos en su tarjeta
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta con movimientos de ingresos y egresos con su respectivas fechas
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite una lista de recomendaciones de uso de la cuenta
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe retornar una lista de recomendaciones de cómo gestionar su dinero basadas en el análisis de la cuenta
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica:** Como cliente, quiero poder recibir recomendaciones de uso de mis tarjetas
**Escenario:** El cliente ha realizado movimientos en su tarjeta
**Dada** una cuenta sin movimientos de ingresos ni egresos
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite una lista de recomendaciones de uso de la cuenta
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe retornar una lista de en qué productos o de qué formas puede empezar a usar su dinero
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

### **Como cliente, quiero tener una tarjeta inteligente que se adapte a mis hábitos de compra**

**Característica:** Como cliente, quiero tener una tarjeta inteligente que se adapte a mis hábitos de compra
**Escenario**: El cliente realiza compras frecuentes en una categoría específica
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta activa con tarjeta inteligente asociada
&ensp;&ensp;&ensp;&ensp;**Y** un historial de compras con más del 30% en restaurantes en los últimos 3 meses
&ensp;&ensp;&ensp;&ensp;**Cuando** el sistema analice los patrones de compra del cliente
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe activar automáticamente beneficios de cashback del 3% en restaurantes
&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente sobre el nuevo beneficio activado
&ensp;&ensp;&ensp;&ensp;**Y** aplicar el beneficio en las próximas compras en dicha categoría
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero tener una tarjeta inteligente que se adapte a mis hábitos de compra
**Escenario**: El cliente cambia sus patrones de compra
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta activa con tarjeta inteligente asociada
&ensp;&ensp;&ensp;&ensp;**Y** beneficios activos de cashback del 3% en "Restaurantes"
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente cambie su patrón de compras a más del 40% en "Supermercados" durante un mes
&ensp;&ensp;&ensp;&ensp;**Y** menos del 15% en "Restaurantes" durante el mismo periodo
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe actualizar los beneficios automáticamente
&ensp;&ensp;&ensp;&ensp;**Y** activar cashback del 3% en "Supermercados"
&ensp;&ensp;&ensp;&ensp;**Y** reducir el cashback en "Restaurantes" al 1%
&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente sobre los cambios en sus beneficios
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero tener una tarjeta inteligente que se adapte a mis hábitos de compra
**Escenario**: El cliente viaja al extranjero
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta activa con tarjeta inteligente asociada
&ensp;&ensp;&ensp;&ensp;**Cuando** el sistema detecte transacciones en un país extranjero
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe activar automáticamente beneficios para viajes internacionales
&ensp;&ensp;&ensp;&ensp;**Y** eliminar temporalmente las comisiones por cambio de divisa
&ensp;&ensp;&ensp;&ensp;**Y** activar seguro de viaje durante el periodo de estancia
&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente sobre los beneficios activados por su viaje
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

### **Como cliente del banco, quiero pagar con el dinero de mi cuenta**

**Característica:** Como cliente del banco, quiero pagar con el dinero de mi cuenta.
**Escenario:** El cliente tiene fondos suficientes en la cuenta que quiere usar.
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta activa con $100.000 COP
&ensp;&ensp;&ensp;&ensp;**Y** una tarjeta valida
&ensp;&ensp;&ensp;&ensp;**Y** un pin válido
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente use la tarjeta en un datáfono para pagar $60.000 COP
&ensp;&ensp;&ensp;&ensp;**Entonces** la cuenta del cliente debe quedar con un saldo de $40.000 COP
&ensp;&ensp;&ensp;&ensp;**Y** el datáfono debe confirmar que la transacción ha sido exitosa
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica:** Como cliente del banco, quiero pagar con el dinero de mi cuenta.
**Escenario:** El cliente no tiene fondos suficientes en la cuenta que quiere usar.
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta activa con $50.000 COP
&ensp;&ensp;&ensp;&ensp;**Y** una tarjeta valida
&ensp;&ensp;&ensp;&ensp;**Y** un pin válido
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente use la tarjeta en un datáfono para pagar $60.000 COP
&ensp;&ensp;&ensp;&ensp;**Entonces** el datáfono debe informar que la transacción no ha sido exitosa por fondos insuficientes
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 400

### **Como cliente del banco, quiero retirar el dinero de mi cuenta**

**Característica:** Como cliente del banco, quiero retirar el dinero de mi cuenta.
**Escenario:** El cliente tiene fondos suficientes en la cuenta que quiere usar.
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta activa con $100.000 COP
&ensp;&ensp;&ensp;&ensp;**Y** una tarjeta valida
&ensp;&ensp;&ensp;&ensp;**Y** un pin válido
&ensp;&ensp;&ensp;&ensp;**Y** el cajero automático cuenta con dinero suficiente
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente inserte la tarjeta en un cajero automático válido
&ensp;&ensp;&ensp;&ensp;**Y** el cliente solicite retirar $60.000 COP
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe actualizar el saldo de la cuenta a $40.000 COP
&ensp;&ensp;&ensp;&ensp;**Y** el cajero automático debe entregar $60.000 COP
&ensp;&ensp;&ensp;&ensp;**Y** el cajero automático debe finalizar la transacción
&ensp;&ensp;&ensp;&ensp;**Y** El cajero automático debe expulsar la tarjeta del cliente
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica:** Como cliente del banco, quiero retirar el dinero de mi cuenta.
**Escenario:** El cliente no tiene fondos suficientes en la cuenta que quiere usar.
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta activa con $40.000 COP
&ensp;&ensp;&ensp;&ensp;**Y** una tarjeta valida
&ensp;&ensp;&ensp;&ensp;**Y** un pin válido
&ensp;&ensp;&ensp;&ensp;**Y** el cajero automático cuenta con dinero suficiente
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente inserte la tarjeta en un cajero automático válido
&ensp;&ensp;&ensp;&ensp;**Y** el cliente solicite retirar $60.000 COP
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe mostrar un mensaje indicando que no se tienen fondos suficientes
&ensp;&ensp;&ensp;&ensp;**Y** el cajero automático debe finalizar la transacción
&ensp;&ensp;&ensp;&ensp;**Y** El cajero automático debe expulsar la tarjeta del cliente
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 400

### **Como cliente, quiero recibir asesoramiento financiero personalizado**

**Característica:** Como cliente, quiero recibir asesoramiento financiero personalizado
**Escenario:** Cliente solicita asesoramiento financiero personalizado
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con una cuenta activa
&ensp;&ensp;&ensp;&ensp;**Y** su perfil financiero completo
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite asesoramiento financiero personalizado
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe analizar su historial financiero
&ensp;&ensp;&ensp;&ensp;**Y** generar recomendaciones adaptadas a su perfil
&ensp;&ensp;&ensp;&ensp;**Y** presentar un plan de acción financiero personalizado
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica:** Como cliente, quiero recibir asesoramiento financiero personalizado
**Escenario:** Cliente sin perfil financiero completo solicita asesoramiento
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con una cuenta activa
&ensp;&ensp;&ensp;&ensp;**Y** su perfil financiero incompleto
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite asesoramiento financiero personalizado
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe solicitar la información faltante
&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente que debe completar su perfil para recibir asesoramiento personalizado
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

### **Como cliente, quiero invertir mi dinero en un portafolio de inversiones dinámico**

**Característica:** Como cliente, quiero invertir mi dinero en un portafolio de inversiones dinámico
**Escenario:** Cliente con fondos suficientes invierte en un portafolio
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con una cuenta activa con $1.000.000 COP
&ensp;&ensp;&ensp;&ensp;**Y** un perfil de riesgo definido
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite invertir $500.000 COP en un portafolio dinámico
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe crear un portafolio adaptado a su perfil de riesgo
&ensp;&ensp;&ensp;&ensp;**Y** transferir $500.000 COP de su cuenta al portafolio
&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente de la creación exitosa del portafolio
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero invertir mi dinero en un portafolio de inversiones dinámico
**Escenario**: Cliente sin fondos suficientes intenta invertir
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con una cuenta activa con $300.000 COP
&ensp;&ensp;&ensp;&ensp;**Y** un perfil de riesgo definido
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite invertir $500.000 COP en un portafolio dinámico
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe rechazar la solicitud
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente que no cuenta con fondos suficientes
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 400

### **Como cliente, quiero configurar planes de ahorro automáticos**

**Característica**: Como cliente, quiero configurar planes de ahorro automáticos
**Escenario**: Cliente configura un plan de ahorro automático periódico
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con una cuenta activa
&ensp;&ensp;&ensp;&ensp;**Y** un ingreso mensual registrado
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite configurar un ahorro automático de 10% de sus ingresos
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe crear un plan de ahorro
&ensp;&ensp;&ensp;&ensp;**Y** programar transferencias automáticas mensuales del 10% de sus ingresos
&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente la configuración exitosa del plan
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero configurar planes de ahorro automáticos
**Escenario**: Cliente modifica un plan de ahorro existente
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con un plan de ahorro automático activo del 10% de sus ingresos
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite modificar el porcentaje a 15%
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe actualizar el plan de ahorro
&ensp;&ensp;&ensp;&ensp;**Y** programar las nuevas transferencias con el 15% de sus ingresos
&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente la actualización exitosa del plan
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

### **Como cliente, quiero solicitar un crédito personalizado**

**Característica**: Como cliente, quiero solicitar un crédito personalizado
**Escenario**: Cliente con buen historial crediticio solicita un crédito
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con una cuenta activa
&ensp;&ensp;&ensp;&ensp;**Y** un historial crediticio bueno
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite un crédito de $5.000.000 COP
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe analizar su capacidad de pago
&ensp;&ensp;&ensp;&ensp;**Y** aprobar el crédito con condiciones favorables
&ensp;&ensp;&ensp;&ensp;**Y** transferir $5.000.000 COP a su cuenta
&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente la aprobación y condiciones del crédito
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero solicitar un crédito personalizado
**Escenario**: Cliente con historial crediticio deficiente solicita un crédito
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con una cuenta activa
&ensp;&ensp;&ensp;&ensp;**Y** un historial crediticio deficiente
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite un crédito de $5.000.000 COP
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe rechazar la solicitud
&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente que su crédito ha sido rechazado por historial crediticio deficiente
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 400

### **Como cliente, quiero recibir alertas ante actividades sospechosas en mi cuenta**

**Característica**: Como cliente, quiero recibir alertas ante actividades sospechosas en mi cuenta
**Escenario**: Se detecta una transacción sospechosa en la cuenta del cliente
&ensp;&ensp;&ensp;&ensp;**Dada** una cuenta activa con patrones de uso normales
&ensp;&ensp;&ensp;&ensp;**Cuando** se registre una transacción que difiere significativamente de los patrones habituales
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe marcar la transacción como sospechosa
&ensp;&ensp;&ensp;&ensp;**Y** enviar una alerta inmediata al cliente
&ensp;&ensp;&ensp;&ensp;**Y** solicitar confirmación de la transacción
&ensp;&ensp;&ensp;&ensp;**Y** bloquear temporalmente la cuenta si no se recibe confirmación
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero recibir alertas ante actividades sospechosas en mi cuenta
**Escenario**: Cliente confirma una transacción marcada como sospechosa
&ensp;&ensp;&ensp;&ensp;**Dada** una transacción marcada como sospechosa
&ensp;&ensp;&ensp;&ensp;**Y** una cuenta temporalmente bloqueada
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente confirme que la transacción es legítima
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe desbloquear la cuenta
&ensp;&ensp;&ensp;&ensp;**Y** procesar la transacción
&ensp;&ensp;&ensp;&ensp;**Y** actualizar los patrones de uso para incluir este tipo de transacción
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

### **Como cliente, quiero acceder a cursos de educación financiera personalizada**

**Característica**: Como cliente, quiero acceder a cursos de educación financiera personalizada
**Escenario**: Cliente solicita recomendaciones de educación financiera
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con un perfil financiero completo
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite recomendaciones de educación financiera
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe analizar su perfil financiero
&ensp;&ensp;&ensp;&ensp;**Y** recomendar cursos y recursos adaptados a sus necesidades
&ensp;&ensp;&ensp;&ensp;**Y** presentar un plan de educación financiera personalizado
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero acceder a cursos de educación financiera personalizada
**Escenario**: Cliente completa un curso de educación financiera
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente inscrito en un curso de educación financiera
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente complete todas las lecciones y evaluaciones del curso
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe registrar el curso como completado
&ensp;&ensp;&ensp;&ensp;**Y** actualizar el perfil financiero del cliente
&ensp;&ensp;&ensp;&ensp;**Y** ofrecer recomendaciones de aplicación práctica
&ensp;&ensp;&ensp;&ensp;**Y** sugerir cursos avanzados relacionados
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

### **Como cliente, quiero integrar mi cuenta con aplicaciones financieras de terceros**

**Característica**: Como cliente, quiero integrar mi cuenta con aplicaciones financieras de terceros
**Escenario**: Cliente solicita conectar su cuenta con una aplicación de terceros autorizada
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con una cuenta activa
&ensp;&ensp;&ensp;&ensp;**Y** una aplicación de terceros autorizada por el banco
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite conectar su cuenta con la aplicación
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe solicitar autorización explícita del cliente
&ensp;&ensp;&ensp;&ensp;**Y** establecer los permisos de acceso específicos
&ensp;&ensp;&ensp;&ensp;**Y** generar tokens de acceso seguro
&ensp;&ensp;&ensp;&ensp;**Y** completar la integración
&ensp;&ensp;&ensp;&ensp;**Y** notificar al cliente la conexión exitosa
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero integrar mi cuenta con aplicaciones financieras de terceros
**Escenario**: Cliente revoca acceso a una aplicación de terceros
&ensp;&ensp;&ensp;&ensp;**Dado** un cliente con una cuenta conectada a una aplicación de terceros
&ensp;&ensp;&ensp;&ensp;**Cuando** el cliente solicite revocar el acceso a dicha aplicación
&ensp;&ensp;&ensp;&ensp;**Entonces** el sistema debe cancelar los tokens de acceso
&ensp;&ensp;&ensp;&ensp;**Y** eliminar los permisos concedidos
&ensp;&ensp;&ensp;&ensp;**Y** notificar a la aplicación de terceros
&ensp;&ensp;&ensp;&ensp;**Y** confirmar al cliente la revocación exitosa del acceso
&ensp;&ensp;&ensp;&ensp;**Y** se retorna un código de estado 200
