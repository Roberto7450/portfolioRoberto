# Política de seguridad

Este documento explica cómo se protege el repositorio **pruebaVehiculo** (código Java y documentación generada con Javadoc): qué archivos no se suben, quién puede hacer qué, cómo se protege la documentación y cómo se recupera el repositorio si algo sale mal.

> El proyecto actual es una aplicación de consola y no contiene contraseñas ni claves. Aun así, las reglas están preparadas para que, si el proyecto crece (conexión a una base de datos, APIs, etc.), nadie suba un secreto por accidente.

## 1. Archivos excluidos del control de versiones (`.gitignore`)

El archivo `.gitignore` indica a Git qué archivos debe ignorar. Se excluyen por estas razones:

| Archivos (patrón) | Tipo | Por qué no se suben |
|---|---|---|
| `.env`, `.env.*`, `*.pem`, `*.key`, `*.p12`, `*.jks`, `*.keystore`, `secrets/`, `credentials.*` | Secretos y claves | Contienen contraseñas, tokens o claves privadas. En un repositorio público los puede leer cualquiera, y Git conserva el historial: aunque se borre el archivo después, el secreto sigue en los commits antiguos. Hay programas automáticos que rastrean GitHub buscando claves. |
| `application-local.*`, `config.local.*`, `local.properties` | Configuración local | Rutas, usuarios y ajustes propios de cada equipo. Subirlos haría que la configuración de una persona rompiera el entorno de otra y puede revelar datos personales. |
| `target/`, `*.class`, `*.jar`, `*.war`, `*.ear` | Binarios compilados | Se regeneran compilando con Maven. Ocupan espacio, provocan conflictos entre compañeros y no se pueden revisar como código: no se sabe qué contienen. |
| `*.log`, `*.tmp`, `*.temp`, `*.bak`, `*.swp`, `*~` | Temporales y registros | No aportan valor al proyecto. Los logs pueden incluir datos personales, rutas del equipo o incluso credenciales en mensajes de error. |
| `.idea/`, `*.iml`, `.vscode/`, `.classpath`, `.project`, `.settings/`, `nbproject/private/`, `bin/`, `out/` | Configuración del editor | Son preferencias personales de cada programador, con rutas absolutas de su equipo. Cada persona usa un editor distinto (NetBeans, VS Code, IntelliJ). |
| `.DS_Store`, `Thumbs.db`, `desktop.ini` | Archivos del sistema operativo | Los crea el sistema (macOS, Windows) y no tienen relación con el proyecto. |
| `backups/`, `*.bundle` | Copias de seguridad locales | Son copias del propio repositorio. Subirlas duplicaría todo el historial dentro del mismo repositorio y no serviría como copia independiente. |

**Qué sí se sube:** el código fuente (`src/`), el `pom.xml`, el `README.md`, este `SECURITY.md`, y la carpeta `javadoc/`. Si algún día hace falta una plantilla de configuración, se sube un `.env.example` **sin valores reales** (el `.gitignore` lo permite expresamente).

**Importante:** `.gitignore` solo afecta a archivos que Git todavía no sigue. Si un archivo ya se subió, hay que dejar de seguirlo con `git rm -r --cached <ruta>` y hacer commit. Si ese archivo contenía un secreto, además hay que seguir los pasos de la sección 4.

## 2. Control de acceso: roles y permisos

Se aplica el **principio de mínimo privilegio**: cada persona recibe solo el permiso que necesita para su trabajo, y los permisos se revisan periódicamente.

| Rol en el equipo | Permiso en GitHub | Justificación |
|---|---|---|
| Propietario / responsable del proyecto (1 o 2 personas) | **Admin** | Gestiona ajustes, colaboradores y protección de ramas. Es el nivel más delicado porque permite acciones destructivas (borrar o transferir el repositorio), por eso se limita a muy pocas personas. |
| Líder técnico / mantenedor | **Maintain** | Gestiona ramas, versiones y pull requests sin acceder a los ajustes más sensibles ni a las acciones destructivas. |
| Desarrollador del equipo | **Write** | Sube ramas y abre pull requests. No puede cambiar los ajustes y, con la rama principal protegida, tampoco puede saltarse la revisión. |
| Coordinador / gestor de tareas | **Triage** | Gestiona issues y pull requests (etiquetas, asignaciones) sin poder modificar el código. |
| Tester, profesor o cliente que solo consulta | **Read** | Puede ver el código y comentar, pero no modificar nada. |

Notas:

- En un repositorio **personal** de GitHub solo existen dos niveles: propietario y colaboradores, y los colaboradores siempre pueden leer y escribir. Los cinco roles anteriores están disponibles en repositorios de **organizaciones**.
- Todos los miembros deben tener activada la **verificación en dos pasos (2FA)** en su cuenta de GitHub.
- Cuando alguien deja de participar en el proyecto, se le retira del repositorio.

## 3. Protección de la documentación y del código

Estas medidas se aplican igual al código y a la documentación, porque la documentación Javadoc se genera a partir de los comentarios del código fuente: quien puede cambiar un comentario cambia la documentación.

- **Rama principal protegida (`main`).** Se crea un ruleset en *Settings → Rules → Rulesets* que exige pull request para modificar `main`, exige al menos **una aprobación**, bloquea los *force push* y impide borrar la rama. Así nadie puede reescribir o destruir el historial por error.
- **Revisión de pull requests.** Todo cambio entra por una pull request revisada por otra persona. Quien revisa comprueba que el cambio es correcto, que los comentarios Javadoc siguen siendo fieles al código y que no se cuela ningún archivo que debería estar ignorado (contraseñas, binarios, configuración local).
- **Documentación regenerable.** La fuente de verdad son los comentarios de `src/`. La carpeta `javadoc/` se regenera con `mvn javadoc:javadoc` en cada versión, de modo que no se edita a mano.
- **Detección de secretos.** Se activan *Secret scanning* y *Push protection* en los ajustes de seguridad del repositorio (*Settings → Advanced Security* o *Code security*, según la versión de la interfaz), para que GitHub avise o bloquee si alguien intenta subir una clave.
- **Historial como registro de auditoría.** Commits pequeños, con mensajes claros, que permiten saber quién cambió qué y cuándo.
- **Copias de seguridad.** Cada clon del repositorio ya es una copia completa del historial. Además, antes de cada entrega se crea una copia independiente **fuera** del repositorio:

  ```
  git clone --mirror https://github.com/Roberto7450/pruebaVehiculo.git pruebaVehiculo-mirror.git
  cd pruebaVehiculo-mirror.git
  git bundle create ../pruebaVehiculo.bundle --all
  git bundle verify ../pruebaVehiculo.bundle
  ```

  El archivo `.bundle` contiene todas las ramas y todo el historial en un único archivo. Se guarda en al menos **dos sitios distintos** (por ejemplo, el disco del equipo y una nube o un disco externo).

## 4. Recuperación ante errores graves o pérdida de datos

| Situación | Cómo se recupera |
|---|---|
| **Un commit erróneo ya subido** | `git revert <hash>` crea un commit nuevo que deshace el anterior sin reescribir el historial; es seguro en ramas compartidas. |
| **Cambios perdidos en local** (por un `reset` o un `rebase`) | `git reflog` muestra los movimientos recientes de la rama; con `git reset --hard "HEAD@{n}"` (o `git switch -c rescate <hash>`) se vuelve al punto anterior. El reflog es local y se conserva durante semanas. |
| **Un archivo borrado o modificado por error** | `git restore <archivo>` si aún no se hizo commit, o `git restore --source=<hash> <archivo>` para recuperarlo de un commit anterior. |
| **Una rama borrada por error** | En GitHub, desde la pull request cerrada, botón **Restore branch**; o con `git reflog` para encontrar el último commit y `git branch rescate <hash>`. |
| **Un *force push* que destruyó historial** | Se evita bloqueándolo en el ruleset. Si ocurre, se recupera desde el clon actualizado de otro miembro del equipo o desde la copia de seguridad. |
| **Repositorio borrado en GitHub** | Se puede restaurar **durante 90 días**: *avatar → Settings → Repositories → Deleted repositories → Restore* (en una organización, desde los ajustes de la organización, solo sus propietarios). Puede tardar hasta una hora en aparecer, no se puede restaurar si formaba parte de una red de forks que no está vacía, y los permisos de equipos no se restauran. |
| **Pérdida total** (repositorio no recuperable o cuenta bloqueada) | Se crea un repositorio nuevo y se sube la copia: `git clone pruebaVehiculo.bundle copia`, `git remote add origin <url-nueva>` y `git push --all origin` (y `git push --tags origin`). |
| **Se subió un secreto por error** | 1) **Revocar o cambiar el secreto inmediatamente** (es lo primero: ya hay que darlo por comprometido). 2) Eliminarlo del historial con `git filter-repo` o BFG Repo-Cleaner y forzar el push, avisando antes al equipo. 3) Todos los miembros vuelven a clonar el repositorio. 4) Comprobar que *Secret scanning* ya no muestra alertas. |

**Limitación:** un clon o un `.bundle` guardan el código y su historial, pero **no** los issues, las pull requests, la wiki ni los ajustes de GitHub. Si el proyecto depende de ellos, hay que exportarlos o anotarlos por separado.

## 5. Cómo informar de un problema de seguridad

Si encuentras una contraseña, una clave o cualquier dato sensible en el repositorio, **no lo publiques en un issue abierto**. Avisa directamente al propietario (@Roberto7450) por GitHub o, si está activada la opción, desde la pestaña **Security → Report a vulnerability**. El secreto se revocará y se eliminará del historial siguiendo la sección 4.
