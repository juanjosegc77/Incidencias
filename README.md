# Incidencias (JAVA + NetBeans + MySQL + Encriptación AES)

## Descripción

Se utilizó el lenguaje de programación JAVA. Para la interfaz gráfica se utilizó SWING de NetBeans. La conexión y consulta a la base de datos se lleva a cabo mediante MySQL. Todos los registros, excepto las claves primarias, se encriptan usando el modo AES.

La base de datos incidencias tiene 3 tablas: alfabetico, vaclic y usuarios. Las tablas alfabetico y vaclic están relacionadas por el campo "id_alf"
* alfabetico - sirve para administrar todos los datos generales de los empleados.
* vaclic - sirve para administrar las incidencias de tipo vacaciones y licencias asociadas a cada empleado.
* usuarios - sirve para administrar el control de acceso a los usuarios del programa.

## Particularidades en la interfaz gráfica

* Los datos se despliegan en JTable
* En la clase Alfabetico se cuenta con un EditText para filtrar los registros considerando tres campos como valores de condición. La tabla se va filtrando mientras se escribe en el campo de búsqueda.
* Para el ingreso y actualización de datos se utiliza validación de datos.
