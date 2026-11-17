# Tomcat: investigación y descripción
 
## Resumen
 
- **Apache Tomcat** es un contenedor de servlets y JSP. 
- **Catalina** es su núcleo: gestiona los contenedores (Engine, Host y Context) y despliega las aplicaciones. Está en `catalina.jar`, dentro de `/usr/share/tomcat10/lib`. 
- **Coyote** es el conector que recibe las peticiones HTTP (puerto 8080) y las pasa a Catalina. Se configura en `/etc/tomcat10/server.xml`. 
- **Jasper** es el motor JSP: compila cada página a un servlet y deja el resultado en `/var/lib/tomcat10/work`. 
- **Manager** y **Host Manager** son aplicaciones web para administrar aplicaciones y hosts virtuales. Están en `/usr/share/tomcat10-admin` y sus usuarios se definen en `/etc/tomcat10/tomcat-users.xml`.
- Los directorios `bin` (scripts) y `lib` (librerías) están en `/usr/share/tomcat10`, `conf` (configuración) en `/etc/tomcat10`, `webapps` (aplicaciones) en `/var/lib/tomcat10` y `logs` (registros) en `/var/log/tomcat10`.
- Una petición entra por Coyote, Catalina la dirige a Engine, Host, Context y Servlet, y la respuesta sale por Coyote.
