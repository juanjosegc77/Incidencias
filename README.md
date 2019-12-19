# Incidencias
Control de empleados y algunas de sus incidencias (JAVA + NetBeans + SQL + Encriptación AES).

El lenguaje de programación es JAVA y para la interfaz gráfica se utilizó SWING de NetBeans. La conexión y consulta a la base de datos se lleva a cabo mediante SQL. Todos los registros, excepto las claves primarias, se encriptan usando el modo AES.

La base de datos incidencias tiene 3 tablas: alfabetico, vaclic y usuarios. Las tablas alfabetico y vaclic están relacionadas por el campo "id_alf"
* alfabetico - sirve para administrar todos los datos generales de los empleados.
* vaclic - sirve para administrar las incidencias de tipo vacaciones y licencias asociadas a cada empleado.
* usuarios - sirve para administrar el control de acceso a los usuarios del programa.
