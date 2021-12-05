# Prueba de Ingreso - Ceiba Software


Patrón de diseño
- MVVM


Contenido del proyecto

Paquetes

- entities: Contiene los modelos según las respuestas de los servicios rest.

- local: Contiene las clases para instanciar la base de datos y los DAO para realizar las consultas.

- remote: Contiene clases e interfaces para las peticiones al api.

- repository: Contiene los repositorios de datos, las clases que reúnen el accesso a los datos a través del api y la bd.

- di: Contiene los módulos definidos para inyección de dependencias con Hilt.

- ui: Contiene las vistas (actividades) relacionadas con usuarios y publicaciones, además de sus viewmodels correspondientes.

- utils: Contiene clases y métodos de utilería para facilitar el acceso a los datos, realizar peticiones al api, almacenar datos.



Consideraciones:

- Las publicaciones consultadas son almacenadas en BD, en caso de existir un error al consultar el api, se muestran datos cacheados en caso de existir. (Mejora experienca de usuario.)
