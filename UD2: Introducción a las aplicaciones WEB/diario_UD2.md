# Diario - UD2: Introducción a las Aplicaciones WEB

**Alumno:** Roberto Martín · 2.º DAW

---

## Qué he aprendido

* **HTTP y HTTPS.** HTTP es el protocolo con el que clientes y servidores se transfieren hipertexto y funciona normalmente en el puerto 80. La diferencia principal con HTTPS es que este último cifra la comunicación con TLS.
* **FTP y SFTP.** FTP sirve para transferir archivos entre equipos por la red, pero no cifra ni las credenciales ni los datos. SFTP hace lo mismo de forma cifrada sobre SSH. Un ejemplo de cliente es FileZilla y uno de servidor es vsftpd.
* **SSH.** Es un protocolo cifrado para administrar otro equipo de forma remota (`ssh usuario@servidor`). También he aprendido que autenticarse con claves es más seguro que con contraseña, porque no se envía la contraseña en cada conexión y es más difícil de adivinar o robar con ataques de fuerza bruta.

Además, he aprendido cómo se organiza una arquitectura web (cliente-servidor, dos y tres capas), los tipos de alojamiento (compartido, VPS y dedicado) y a instalar y arrancar Apache en Ubuntu.

## Qué no entiendo

Lo que más me cuesta es **Docker**, los puertos, los volúmenes y la comunicación entre contenedores.

## Qué es lo que más me ha gustado y qué es lo que menos

**Lo que más me ha gustado:** instalar Ubuntu en VirtualBox. Crear la máquina virtual desde cero (4 GB de RAM, 50 GB de disco y red NAT), instalar el sistema y después las Guest Additions. Tener un sistema operativo completo funcionando dentro de otro es muy útil para poder probar cosas sin miedo a romper nada.

**Lo que menos me ha gustado:** los comandos de Docker en terminal. Hay muchos parámetros (`-y`,`-d`, `-p`) y cualquier error al escribirlos hace que el contenedor no arranque. Cuesta recordar qué hace cada uno y es fácil equivocarse.

## Qué más me gustaría saber relacionado con la Unidad

* **Montar un servidor FTP/SFTP o SSH propio:** en esta unidad hemos visto la teoría y para qué sirven, pero estaría bien instalar y configurar uno real (por ejemplo, vsftpd o un servidor SSH con claves) y probarlo con un cliente.
* **Docker Compose y volúmenes:** para poder levantar varios contenedores a la vez con un solo archivo y para que los datos no se pierdan al borrar un contenedor. Creo que eso ayudaría a entender mejor Docker.
