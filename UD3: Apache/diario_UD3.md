# Diario - UD3: Apache

**Alumno:** Roberto Martín · 2.º DAW

---

## Qué he aprendido

* **Apache y los VirtualHost.** He aprendido a publicar una web propia sin tocar el sitio por defecto: se crea la carpeta (`/var/www/gci`), se copia `000-default.conf` a un fichero nuevo, se ajustan `ServerName` y `DocumentRoot`, se activa con `a2ensite`, se valida con `apache2ctl configtest` y se recarga con `systemctl reload apache2`.
* **Control de acceso.** He aprendido la diferencia entre autenticación y autorización. Con `htpasswd` creé usuarios para proteger `/privado/` (autenticación básica) y con `Require ip 127.0.0.1 ::1` limité `/interno/` a la propia máquina. Probando con `curl` vi la diferencia entre un 401 (hay que identificarse) y un 403 (no tienes permiso).
* **HTTPS y certificados.** He entendido que TLS da confidencialidad, integridad y autenticación, y que un certificado autofirmado cifra igual que uno de una CA, pero el navegador no se fía de él.
* **Nginx.** He visto que funciona parecido a Apache pero con otra sintaxis: el `server` equivale al VirtualHost, `server_name` a `ServerName` y `root` a `DocumentRoot`, y que hay que recargar siempre tras un cambio.

## Qué no entiendo

Tuve conflictos con los puertos ya que Apache ocupaba el puerto 80 y no se detenía correctamente. Además, no he entendido muy bien los certificados.

## Qué es lo que más me ha gustado y qué es lo que menos

**Lo que más me ha gustado:** crear el VirtualHost y la web personalizada en Apache. Escribir la página, configurar el sitio y ver que aparecía al abrir `gci.example.com` en el navegador me ayudó a entender la relación entre el nombre de dominio y el `DocumentRoot`.

**Lo que menos me ha gustado:** los errores de configuración difíciles de encontrar. A veces un fallo pequeño (una ruta mal escrita, un permiso del `.htpasswd`, olvidar recargar el servicio) da un error o no cambia nada, y hasta dar con la causa se pierde mucho tiempo.

## Qué más me gustaría saber relacionado con la Unidad

* **Let's Encrypt y Certbot con un dominio real:** me gustaría probar con un dominio de verdad para ver cómo se obtiene y se renueva automáticamente un certificado de confianza.
* **Configurar HTTPS también en Nginx:** para comparar cómo se configuran el certificado y la redirección en cada servidor.
