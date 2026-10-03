# Reflexión: documentar un proyecto con Javadoc

Para esta práctica he documentado con **Javadoc** el proyecto `pruebaVehiculo`, una aplicación de consola en Java del primer año de DAM.

## ¿Qué tan fácil fue usar la herramienta?

Fue bastante fácil. Las etiquetas básicas (`@param`, `@return` y `@throws`) se aprenden enseguida y, una vez escritos los comentarios, la documentación se genera con un solo comando y queda como una web navegable (`javadoc/index.html`). Lo que más tiempo llevó fue escribir comentarios útiles, no manejar la herramienta. Además, documentar el código ayudó a detectar fallos, como un constructor que ignoraba el DNI, porque obliga a explicar qué hace realmente cada método.

## Ventajas y desventajas

**Ventajas**
- Es gratuita, viene incluida en el JDK y es el estándar de Java.
- La documentación está junto al código, y los editores muestran esos comentarios al pasar el ratón por un método.
- Se integra con Maven y genera una web HTML con buen aspecto sin mucho esfuerzo.

**Desventajas**
- De forma nativa solo genera HTML: para obtener el PDF hay que imprimir las páginas desde el navegador, y para Markdown haría falta una herramienta externa.
- Hay que mantener los comentarios actualizados a mano: si cambia el código y nadie cambia el comentario, la documentación miente.
- Con tildes y eñes hay que configurar la codificación UTF-8, y el diseño de la web es algo clásico.
- Solo sirve para Java.

## ¿La recomendaría para un proyecto colaborativo?

Sí. Todo el equipo comenta con el mismo formato, la documentación se regenera cuando cambia el código y puede subirse al repositorio para que cualquiera la consulte. Para que funcione bien conviene acordar unas normas sencillas (por ejemplo, documentar siempre lo público) y volver a generarla antes de cada entrega. En un proyecto grande se podría automatizar con integración continua.
