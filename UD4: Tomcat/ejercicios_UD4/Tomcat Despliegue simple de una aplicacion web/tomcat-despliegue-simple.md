# Tomcat: Despliegue simple de una aplicación web

## 1. Crear una aplicación web y generar el archivo WAR

Se ha creado una aplicación JSP sencilla llamada `holamundo`, que muestra un mensaje de bienvenida. Después, se ha generado el archivo `holamundo.war`.

![Captura 1: creación del archivo WAR](capturas/1.png)

## 2. Desplegar el WAR en la carpeta webapps

Se ha copiado el archivo `holamundo.war` en la carpeta `webapps` de Tomcat para que pueda desplegarse.

![Captura 2: archivo WAR en webapps](capturas/2.png)

## 3. Observar el despliegue automático de Tomcat

Tomcat detecta el archivo WAR, lo descomprime en una carpeta con el mismo nombre, crea el contexto de la aplicación y la prepara para recibir peticiones.

![Captura 3: despliegue automático](capturas/3.png)

## 4. Acceder a la aplicación web

Se ha accedido a `http://localhost:8080/holamundo` desde el navegador y se ha comprobado que la aplicación muestra correctamente el mensaje «¡Hola Mundo!».

![Captura 4: aplicación funcionando](capturas/4.png)