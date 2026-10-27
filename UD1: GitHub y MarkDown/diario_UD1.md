# Diario - UD1: GitHub y MarkDown

**Alumno:** Roberto Martín · 2.º DAW

---

## Qué he aprendido

- **Seguridad del repositorio.** He entendido para qué sirve el `.gitignore`, que los secretos (`.env`, claves) no se suben porque Git conserva el historial y, aunque se borre el archivo, el secreto sigue en los commits antiguos. También he aprendido que `.gitignore` solo afecta a archivos que Git todavía no sigue y que, si ya se subieron, hay que usar `git rm -r --cached`. Con `SECURITY.md` he aprendido a dejar por escrito la política de seguridad del proyecto.
- **Recuperación ante errores.** Sé qué hacer según el problema: `git revert` para deshacer un commit ya subido, `git reflog` para recuperar cambios perdidos, `git restore` para recuperar un archivo y los `.bundle` como copia de seguridad independiente del repositorio.
- **Roles y permisos de colaboradores.** He aprendido que en un repositorio personal solo hay propietario y colaboradores (y estos siempre pueden escribir), y que los roles Read, Triage, Write, Maintain y Admin existen en organizaciones. También he comprendido el principio de mínimo privilegio: cada persona tiene solo el permiso que necesita.

## Qué no entiendo

Lo que menos entiendo es **escribir en Markdown**. Me cuesta aprender la sintaxis (tablas, enlaces, imágenes, bloques de código) y muchas veces tengo que comprobar cómo se ve el resultado para saber si está bien escrito.

## Qué es lo que más me ha gustado y qué es lo que menos

**Lo que más me ha gustado:** probar los roles con mi compañero Aarón. Ver en la práctica cómo con el rol *Read* el `git push` falla con un error 403, cómo con *Write* sí se puede subir, y cómo con *Admin* aparece la *Danger Zone* en Settings me ayudó a entender los permisos mucho mejor que si solo los hubiera leído.

**Lo que menos me ha gustado:** hacer capturas y documentar cada paso. Es útil para dejar constancia del trabajo, pero se hace pesado tener que parar a capturar y explicar lo que estás haciendo.

## Qué más me gustaría saber relacionado con la Unidad

- **Flujos de trabajo en equipo (Git Flow, forks):** para saber cómo se organizan las ramas y las contribuciones en un proyecto real con varias personas.
- **Automatización con GitHub Actions (integración continua):** por ejemplo, para que la documentación Javadoc se regenere sola o para que se compruebe el código en cada pull request.
