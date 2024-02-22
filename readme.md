# Actividad: El Problema del Barbero Dormilón en Java

### 1.Decisión del Cliente: Considera cómo un cliente decide si esperará o se irá cuando llegue a la barbería. ¿Qué factores influyen en esta decisión y cómo se puede implementar esta 
### lógica de manera que sea coherente con el comportamiento esperado?

Cuando un cliente llega, decide si esperar o irse según la disponibilidad de sillas. Si no hay espacio, se va. Si hay, se sienta y espera al barbero.
Esto se maneja en el método clienteNuevo de la clase Barberia.

### 2.Manejo de la Cola de Espera: Reflexiona sobre la estructura de datos que podría representar mejor la cola de espera. ¿Cómo garantizarías que los clientes sean atendidos en el orden 
### correcto, especialmente cuando el barbero se desocupa y está listo para atender al siguiente cliente?

La cola de espera es una lista (Queue<Cliente> sillas). Cuando un cliente llega, se agrega a la cola de espera (sillas) si hay espacio y el barbero toma al próximo cliente en orden 
cuando está listo para cortar el pelo. Si no hay espacio en la sillas de espera el cliente se va (Interrumpimos su hilo)

### 3.Concurrencia y Sincronización: Piensa en cómo gestionarías la concurrencia en este escenario. ¿Cómo asegurarías que el barbero no sea despertado por un cliente cuando ya 
### está atendiendo a otro? ¿Cómo manejarías las situaciones en las que múltiples clientes llegan al mismo tiempo cuando solo queda una silla de espera disponible?

Se utilizan locks y condiciones para manejar la concurrencia. El barbero espera si no hay clientes y se despierta cuando llega uno. Antes de despertar al barbero, 
verifico que la cola de sillas no esté vacía con (while (sillas.isEmpty())). Esto evita que el barbero se despierte si no hay clientes esperando.
Una vez que un cliente llega y se sienta en una silla, el barbero es despertado con barberoDurmiendo.signal()(Esto también avisa de que hay un cliente
listo para ser atentido). Los clientes esperan su turno para cuando el barbero esté libre.

Cuando varios clientes llegan al mismo tiempo y solo queda una silla disponible, el uso del candado (lock) garantiza que las operaciones en la cola de espera (sillas) 
se realicen de manera segura y secuencial. El lock asegura que no puedan haber conflitos entre hilos intentando modificar la cola al mismo tiempo y se mezclen recursos.

### 4.Justicia y Eficiencia: Considera el equilibrio entre la justicia (asegurando que todos los clientes tengan una oportunidad justa de ser atendidos) y la eficiencia (minimizando el 
### tiempo de espera para los clientes y el tiempo inactivo para el barbero). ¿Cómo impactan tus decisiones de diseño en este equilibrio?

El diseño busca un equilibrio justo. La cola de entrada y salida garantiza un trato justo a los clientes en el orden de llegada. La eficiencia se mantiene al despertar al barbero solo 
cuando hay clientes y al utilizar condiciones para gestionar la concurrencia de manera controlada. En resumen, trata a todos por igual sin perder eficiencia. 
