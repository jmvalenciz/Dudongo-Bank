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
	**Dada** una persona con un historial crediticio bueno
	**Y** un documento de identidad válido y vigente
	**Cuando** el cliente solicite crear una cuenta
	**Entonces** el sistema debe crearle una cuenta
	**Y** solicitarle sus preferencias financieras
	**Y** crearle un producto de cuenta débito a su nombre
	**Y** se retorna un código de estado 200

**Característica:** Como persona natural, quiero poder crear una cuenta
**Escenario:** La persona ya tiene una cuenta a su nombr
	**Dada** una persona con un historial crediticio buen
	**Y** un documento de identidad válido y vigente
	**Cuando** el cliente solicite crear una cuenta
	**Entonces** el sistema debe informarle que ya hay una cuenta asociada a esta persona
	**Y** el sistema debe finalizar el proceso de creación de cuenta
	**Y** se retorna un código de estado 400

**Característica:** Como persona natural, quiero poder crear una cuenta
**Escenario:** La persona no tiene un buen historial crediticio
	**Dada** una persona con un historial crediticio deficiente
	**Y** un documento de identidad válido y vigente
	**Cuando** el cliente solicite crear una cuenta
	**Entonces** el sistema debe informarle que no tiene un historial crediticio lo suficientemente bueno como para crear una cuenta
	**Y** el sistema debe finalizar el proceso de creación de cuenta
	**Y** se retorna un código de estado 400

**Característica:** Como persona natural, quiero poder crear una cuenta
**Escenario:** La persona no tiene un documento de identidad válido
**Dada** una persona con un documento de identidad no válido
**O** una persona con un documento de identidad no reconocido a nivel nacional
**Cuando** el cliente solicite crear una cuenta
**Entonces** el sistema debe informarle que no cuenta con un documento de identidad válido
**Y** el sistema debe finalizar el proceso de creación de cuenta
**Y** se retorna un código de estado 400

### **Como cliente, quiero ver la lista de ingresos y egresos de mi cuenta**

**Característica:** Como cliente, quiero ver la lista de ingresos y egresos de mi cuenta
**Escenario:** El usuario final ha realizado movimientos con su cuenta.
	**Dada** una cuenta con 5 movimientos de ingresos y egresos
	**Cuando** el cliente solicite la lista de movimientos de la cuenta
	**Entonces** el sistema debe retornar la lista de 5 movimientos de la cuenta con su respectiva fecha y detalles de la transacción
	**Y** se retorna un código de estado 200

**Característica:** Como cliente, quiero ver la lista de ingresos y egresos de mi cuenta
**Escenario:** El usuario final no ha realizado gastos con su cuenta.
	**Dada** una cuenta con movimientos de ingresos y egresos con su respectivas fechas
	**Cuando** el cliente solicite la lista de movimientos de la cuenta
	**Entonces** el sistema debe retornar la lista vacía de movimientos de la cuenta
	**Y** se retorna un código de estado 200

### **Como cliente, quiero poder recibir recomendaciones de uso de mis tarjetas**

**Característica:** Como cliente, quiero poder recibir recomendaciones de uso de mis tarjetas
**Escenario:** El cliente ha realizado movimientos en su tarjeta
	**Dada** una cuenta con movimientos de ingresos y egresos con su respectivas fechas
	**Cuando** el cliente solicite una lista de recomendaciones de uso de la cuenta
	**Entonces** el sistema debe retornar una lista de recomendaciones de cómo gestionar su dinero basadas en el análisis de la cuenta
	**Y** se retorna un código de estado 200

**Característica:** Como cliente, quiero poder recibir recomendaciones de uso de mis tarjetas
**Escenario:** El cliente ha realizado movimientos en su tarjeta
**Dada** una cuenta sin movimientos de ingresos ni egresos
	**Cuando** el cliente solicite una lista de recomendaciones de uso de la cuenta
	**Entonces** el sistema debe retornar una lista de en qué productos o de qué formas puede empezar a usar su dinero
	**Y** se retorna un código de estado 200

### **Como cliente, quiero tener una tarjeta inteligente que se adapte a mis hábitos de compra**

**Característica:** Como cliente, quiero tener una tarjeta inteligente que se adapte a mis hábitos de compra
**Escenario**: El cliente realiza compras frecuentes en una categoría específica
	**Dada** una cuenta activa con tarjeta inteligente asociada
	**Y** un historial de compras con más del 30% en restaurantes en los últimos 3 meses
	**Cuando** el sistema analice los patrones de compra del cliente
	**Entonces** el sistema debe activar automáticamente beneficios de cashback del 3% en restaurantes
	**Y** notificar al cliente sobre el nuevo beneficio activado
	**Y** aplicar el beneficio en las próximas compras en dicha categoría
	**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero tener una tarjeta inteligente que se adapte a mis hábitos de compra
**Escenario**: El cliente cambia sus patrones de compra
	**Dada** una cuenta activa con tarjeta inteligente asociada
	**Y** beneficios activos de cashback del 3% en "Restaurantes"
	**Cuando** el cliente cambie su patrón de compras a más del 40% en "Supermercados" durante un mes
	**Y** menos del 15% en "Restaurantes" durante el mismo periodo
	**Entonces** el sistema debe actualizar los beneficios automáticamente
	**Y** activar cashback del 3% en "Supermercados"
	**Y** reducir el cashback en "Restaurantes" al 1%
	**Y** notificar al cliente sobre los cambios en sus beneficios
	**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero tener una tarjeta inteligente que se adapte a mis hábitos de compra
**Escenario**: El cliente viaja al extranjero
	**Dada** una cuenta activa con tarjeta inteligente asociada
	**Cuando** el sistema detecte transacciones en un país extranjero
	**Entonces** el sistema debe activar automáticamente beneficios para viajes internacionales
	**Y** eliminar temporalmente las comisiones por cambio de divisa
	**Y** activar seguro de viaje durante el periodo de estancia
	**Y** notificar al cliente sobre los beneficios activados por su viaje
	**Y** se retorna un código de estado 200

### **Como cliente del banco, quiero pagar con el dinero de mi cuenta**

**Característica:** Como cliente del banco, quiero pagar con el dinero de mi cuenta.
**Escenario:** El cliente tiene fondos suficientes en la cuenta que quiere usar.
	**Dada** una cuenta activa con $100.000 COP
	**Y** una tarjeta valida
	**Y** un pin válido
	**Cuando** el cliente use la tarjeta en un datáfono para pagar $60.000 COP
	**Entonces** la cuenta del cliente debe quedar con un saldo de $40.000 COP
	**Y** el datáfono debe confirmar que la transacción ha sido exitosa
	**Y** se retorna un código de estado 200

**Característica:** Como cliente del banco, quiero pagar con el dinero de mi cuenta.
**Escenario:** El cliente no tiene fondos suficientes en la cuenta que quiere usar.
	**Dada** una cuenta activa con $50.000 COP
	**Y** una tarjeta valida
	**Y** un pin válido
	**Cuando** el cliente use la tarjeta en un datáfono para pagar $60.000 COP
	**Entonces** el datáfono debe informar que la transacción no ha sido exitosa por fondos insuficientes
	**Y** se retorna un código de estado 400

### **Como cliente del banco, quiero retirar el dinero de mi cuenta**

**Característica:** Como cliente del banco, quiero retirar el dinero de mi cuenta.
**Escenario:** El cliente tiene fondos suficientes en la cuenta que quiere usar.
	**Dada** una cuenta activa con $100.000 COP
	**Y** una tarjeta valida
	**Y** un pin válido
	**Y** el cajero automático cuenta con dinero suficiente
	**Cuando** el cliente inserte la tarjeta en un cajero automático válido
	**Y** el cliente solicite retirar $60.000 COP
	**Entonces** el sistema debe actualizar el saldo de la cuenta a $40.000 COP
	**Y** el cajero automático debe entregar $60.000 COP
	**Y** el cajero automático debe finalizar la transacción
	**Y** El cajero automático debe expulsar la tarjeta del cliente
	**Y** se retorna un código de estado 200

**Característica:** Como cliente del banco, quiero retirar el dinero de mi cuenta.
**Escenario:** El cliente no tiene fondos suficientes en la cuenta que quiere usar.
	**Dada** una cuenta activa con $40.000 COP
	**Y** una tarjeta valida
	**Y** un pin válido
	**Y** el cajero automático cuenta con dinero suficiente
	**Cuando** el cliente inserte la tarjeta en un cajero automático válido
	**Y** el cliente solicite retirar $60.000 COP
	**Entonces** el sistema debe mostrar un mensaje indicando que no se tienen fondos suficientes
	**Y** el cajero automático debe finalizar la transacción
	**Y** El cajero automático debe expulsar la tarjeta del cliente
	**Y** se retorna un código de estado 400

### **Como cliente, quiero recibir asesoramiento financiero personalizado**

**Característica:** Como cliente, quiero recibir asesoramiento financiero personalizado
**Escenario:** Cliente solicita asesoramiento financiero personalizado
	**Dado** un cliente con una cuenta activa
	**Y** su perfil financiero completo
	**Cuando** el cliente solicite asesoramiento financiero personalizado
	**Entonces** el sistema debe analizar su historial financiero
	**Y** generar recomendaciones adaptadas a su perfil
	**Y** presentar un plan de acción financiero personalizado
	**Y** se retorna un código de estado 200

**Característica:** Como cliente, quiero recibir asesoramiento financiero personalizado
**Escenario:** Cliente sin perfil financiero completo solicita asesoramiento
	**Dado** un cliente con una cuenta activa
	**Y** su perfil financiero incompleto
	**Cuando** el cliente solicite asesoramiento financiero personalizado
	**Entonces** el sistema debe solicitar la información faltante
	**Y** notificar al cliente que debe completar su perfil para recibir asesoramiento personalizado
	**Y** se retorna un código de estado 200

### **Como cliente, quiero invertir mi dinero en un portafolio de inversiones dinámico**

**Característica:** Como cliente, quiero invertir mi dinero en un portafolio de inversiones dinámico
**Escenario:** Cliente con fondos suficientes invierte en un portafolio
	**Dado** un cliente con una cuenta activa con $1.000.000 COP
	**Y** un perfil de riesgo definido
	**Cuando** el cliente solicite invertir $500.000 COP en un portafolio dinámico
	**Entonces** el sistema debe crear un portafolio adaptado a su perfil de riesgo
	**Y** transferir $500.000 COP de su cuenta al portafolio
	**Y** notificar al cliente de la creación exitosa del portafolio
	**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero invertir mi dinero en un portafolio de inversiones dinámico
**Escenario**: Cliente sin fondos suficientes intenta invertir
	**Dado** un cliente con una cuenta activa con $300.000 COP
	**Y** un perfil de riesgo definido
	**Cuando** el cliente solicite invertir $500.000 COP en un portafolio dinámico
	**Entonces** el sistema debe rechazar la solicitud
		**Y** notificar al cliente que no cuenta con fondos suficientes
	**Y** se retorna un código de estado 400

### **Como cliente, quiero configurar planes de ahorro automáticos**

**Característica**: Como cliente, quiero configurar planes de ahorro automáticos
**Escenario**: Cliente configura un plan de ahorro automático periódico
	**Dado** un cliente con una cuenta activa
	**Y** un ingreso mensual registrado
	**Cuando** el cliente solicite configurar un ahorro automático de 10% de sus ingresos
	**Entonces** el sistema debe crear un plan de ahorro
	**Y** programar transferencias automáticas mensuales del 10% de sus ingresos
	**Y** notificar al cliente la configuración exitosa del plan
	**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero configurar planes de ahorro automáticos
**Escenario**: Cliente modifica un plan de ahorro existente
	**Dado** un cliente con un plan de ahorro automático activo del 10% de sus ingresos
	**Cuando** el cliente solicite modificar el porcentaje a 15%
	**Entonces** el sistema debe actualizar el plan de ahorro
	**Y** programar las nuevas transferencias con el 15% de sus ingresos
	**Y** notificar al cliente la actualización exitosa del plan
	**Y** se retorna un código de estado 200

### **Como cliente, quiero solicitar un crédito personalizado**

**Característica**: Como cliente, quiero solicitar un crédito personalizado
**Escenario**: Cliente con buen historial crediticio solicita un crédito
	**Dado** un cliente con una cuenta activa
	**Y** un historial crediticio bueno
	**Cuando** el cliente solicite un crédito de $5.000.000 COP
	**Entonces** el sistema debe analizar su capacidad de pago
	**Y** aprobar el crédito con condiciones favorables
	**Y** transferir $5.000.000 COP a su cuenta
	**Y** notificar al cliente la aprobación y condiciones del crédito
	**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero solicitar un crédito personalizado
**Escenario**: Cliente con historial crediticio deficiente solicita un crédito
	**Dado** un cliente con una cuenta activa
	**Y** un historial crediticio deficiente
	**Cuando** el cliente solicite un crédito de $5.000.000 COP
	**Entonces** el sistema debe rechazar la solicitud
	**Y** notificar al cliente que su crédito ha sido rechazado por historial crediticio deficiente
	**Y** se retorna un código de estado 400

### **Como cliente, quiero recibir alertas ante actividades sospechosas en mi cuenta**

**Característica**: Como cliente, quiero recibir alertas ante actividades sospechosas en mi cuenta
**Escenario**: Se detecta una transacción sospechosa en la cuenta del cliente
	**Dada** una cuenta activa con patrones de uso normales
	**Cuando** se registre una transacción que difiere significativamente de los patrones habituales
	**Entonces** el sistema debe marcar la transacción como sospechosa
	**Y** enviar una alerta inmediata al cliente
	**Y** solicitar confirmación de la transacción
	**Y** bloquear temporalmente la cuenta si no se recibe confirmación
	**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero recibir alertas ante actividades sospechosas en mi cuenta
**Escenario**: Cliente confirma una transacción marcada como sospechosa
	**Dada** una transacción marcada como sospechosa
	**Y** una cuenta temporalmente bloqueada
	**Cuando** el cliente confirme que la transacción es legítima
	**Entonces** el sistema debe desbloquear la cuenta
	**Y** procesar la transacción
	**Y** actualizar los patrones de uso para incluir este tipo de transacción
	**Y** se retorna un código de estado 200

### **Como cliente, quiero acceder a cursos de educación financiera personalizada**

**Característica**: Como cliente, quiero acceder a cursos de educación financiera personalizada
**Escenario**: Cliente solicita recomendaciones de educación financiera
	**Dado** un cliente con un perfil financiero completo
	**Cuando** el cliente solicite recomendaciones de educación financiera
	**Entonces** el sistema debe analizar su perfil financiero
	**Y** recomendar cursos y recursos adaptados a sus necesidades
	**Y** presentar un plan de educación financiera personalizado
	**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero acceder a cursos de educación financiera personalizada
**Escenario**: Cliente completa un curso de educación financiera
	**Dado** un cliente inscrito en un curso de educación financiera
	**Cuando** el cliente complete todas las lecciones y evaluaciones del curso
	**Entonces** el sistema debe registrar el curso como completado
	**Y** actualizar el perfil financiero del cliente
	**Y** ofrecer recomendaciones de aplicación práctica
	**Y** sugerir cursos avanzados relacionados
	**Y** se retorna un código de estado 200

### **Como cliente, quiero integrar mi cuenta con aplicaciones financieras de terceros**

**Característica**: Como cliente, quiero integrar mi cuenta con aplicaciones financieras de terceros
**Escenario**: Cliente solicita conectar su cuenta con una aplicación de terceros autorizada
	**Dado** un cliente con una cuenta activa
	**Y** una aplicación de terceros autorizada por el banco
	**Cuando** el cliente solicite conectar su cuenta con la aplicación
	**Entonces** el sistema debe solicitar autorización explícita del cliente
	**Y** establecer los permisos de acceso específicos
	**Y** generar tokens de acceso seguro
	**Y** completar la integración
	**Y** notificar al cliente la conexión exitosa
	**Y** se retorna un código de estado 200

**Característica**: Como cliente, quiero integrar mi cuenta con aplicaciones financieras de terceros
**Escenario**: Cliente revoca acceso a una aplicación de terceros
	**Dado** un cliente con una cuenta conectada a una aplicación de terceros
	**Cuando** el cliente solicite revocar el acceso a dicha aplicación
	**Entonces** el sistema debe cancelar los tokens de acceso
	**Y** eliminar los permisos concedidos
	**Y** notificar a la aplicación de terceros
	**Y** confirmar al cliente la revocación exitosa del acceso
	**Y** se retorna un código de estado 200
