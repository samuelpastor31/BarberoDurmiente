# Actividad: El Problema del Barbero Dormilón en Java

### 1.Decisión del Cliente: Considera cómo un cliente decide si esperará o se irá cuando llegue a la barbería. ¿Qué factores influyen en esta decisión y cómo se puede implementar esta 
### lógica de manera que sea coherente con el comportamiento esperado?

Cuando un cliente llega, decide si esperar o irse según la disponibilidad de sillas. Si no hay espacio, se va. Si hay, se sienta y espera al barbero.

### 2.Manejo de la Cola de Espera: Reflexiona sobre la estructura de datos que podría representar mejor la cola de espera. ¿Cómo garantizarías que los clientes sean atendidos en el orden 
### correcto, especialmente cuando el barbero se desocupa y está listo para atender al siguiente cliente?

La cola de espera es simplemente una lista (Queue). Los clientes se añaden a la cola y el barbero toma al próximo cliente en orden cuando está listo para cortar el pelo.

### 3.Concurrencia y Sincronización: Piensa en cómo gestionarías la concurrencia en este escenario. ¿Cómo asegurarías que el barbero no sea despertado por un cliente cuando ya 
### está atendiendo a otro? ¿Cómo manejarías las situaciones en las que múltiples clientes llegan al mismo tiempo cuando solo queda una silla de espera disponible?

Se utilizan locks y condiciones para manejar la concurrencia. El barbero espera si no hay clientes y se despierta cuando llega uno. Los clientes esperan su turno y 
señalan al barbero cuando están listos para ser atendidos.

### 4.Justicia y Eficiencia: Considera el equilibrio entre la justicia (asegurando que todos los clientes tengan una oportunidad justa de ser atendidos) y la eficiencia (minimizando el 
### tiempo de espera para los clientes y el tiempo inactivo para el barbero). ¿Cómo impactan tus decisiones de diseño en este equilibrio?

El diseño busca un equilibrio justo. La cola de entrada y salida garantiza un trato justo a los clientes en el orden de llegada. La eficiencia se mantiene al despertar al barbero solo 
cuando hay clientes y al utilizar condiciones para gestionar la concurrencia de manera controlada. En resumen, trata a todos por igual sin perder eficiencia. 
