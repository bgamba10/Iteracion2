CREATE TABLE categoria (
  cat_id NUMBER(11) NOT NULL ,
  con_nombre varchar(100) NOT NULL 
);


INSERT INTO categoria (cat_id, con_nombre) VALUES(1, 'Drama');
INSERT INTO categoria (cat_id, con_nombre) VALUES(2, 'Teatro mudo');
INSERT INTO categoria (cat_id, con_nombre) VALUES(3, 'TÌteres');
INSERT INTO categoria (cat_id, con_nombre) VALUES(4, 'Comparsa');
INSERT INTO categoria (cat_id, con_nombre) VALUES(5, 'Opera');
INSERT INTO categoria (cat_id, con_nombre) VALUES(6, 'Zarzuela');
INSERT INTO categoria (cat_id, con_nombre) VALUES(7, 'Musical');
INSERT INTO CATEGORIA (CAT_ID, con_nombre) VALUES(8, 'Concierto');


--unicidad

--Inserte una tupla1 con una PK conocida y nueva

INSERT INTO CATEGORIA (CAT_ID, con_nombre) VALUES(8, 'COMEDIA');

INSERT INTO CATEGORIA (CAT_ID, con_nombre) VALUES(10, 'COMEDIA');


--Inserte una tupla2, con la misma PK que la tupla 1

INSERT INTO CATEGORIA (CAT_ID, con_nombre) VALUES(10, 'SUSTO');


CREATE TABLE ciudad (
  ciu_id NUMBER(11) NOT NULL, 
  ciu_nombre varchar(100) NOT NULL 
);

INSERT INTO ciudad (ciu_id, ciu_nombre) VALUES(1, 'Bogota');
INSERT INTO ciudad (ciu_id, ciu_nombre) VALUES(2, 'Medellin');
INSERT INTO ciudad (ciu_id, ciu_nombre) VALUES(3, 'Cali');
INSERT INTO ciudad (ciu_id, ciu_nombre) VALUES(4, 'Barranquilla');
INSERT INTO ciudad (ciu_id, ciu_nombre) VALUES(5, 'Villavicencio');

CREATE TABLE compania (
  com_id NUMBER(11) NOT NULL ,
  com_nombre varchar(100) NOT NULL ,
  pai_id NUMBER(11) NOT NULL ,
  com_pagina_web varchar(50) NOT NULL ,
  com_fecha_llegada date NOT NULL ,
  com_fecha_salida date NOT NULL ,
  fes_id NUMBER(11) NOT NULL ,
  com_representante varchar(100) NOT NULL 
);

INSERT INTO compania (com_id, com_nombre, pai_id, com_pagina_web, com_fecha_llegada, com_fecha_salida, fes_id, com_representante) VALUES(1, 'Danzarte', 6, 'www.danzarte.com', TO_DATE('2017-04-02','YYYY-MM-DD'), TO_DATE('2017-04-09','YYYY-MM-DD'), 3, 'Armando Flores');
INSERT INTO compania (com_id, com_nombre, pai_id, com_pagina_web, com_fecha_llegada, com_fecha_salida, fes_id, com_representante) VALUES(2, 'Cantarte', 2, 'www.cantarte.com', TO_DATE('2017-04-03','YYYY-MM-DD'), TO_DATE('2017-04-10','YYYY-MM-DD'), 2, 'Pedro Cipagauta');
INSERT INTO compania (com_id, com_nombre, pai_id, com_pagina_web, com_fecha_llegada, com_fecha_salida, fes_id, com_representante) VALUES(3, 'Presentarte SA', 3, 'www.presentarte.com', TO_DATE('2017-04-04','YYYY-MM-DD'), TO_DATE('2017-04-09','YYYY-MM-DD'), 1, 'Uldarico Manosalva');
INSERT INTO compania (com_id, com_nombre, pai_id, com_pagina_web, com_fecha_llegada, com_fecha_salida, fes_id, com_representante) VALUES(4, 'El mejor show', 4, 'www.elmejorshow.com', TO_DATE('2017-04-09','YYYY-MM-DD'), TO_DATE('2017-04-14','YYYY-MM-DD'), 1, 'Maximiliano Gasca');
INSERT INTO compania (com_id, com_nombre, pai_id, com_pagina_web, com_fecha_llegada, com_fecha_salida, fes_id, com_representante) VALUES(5, 'Expresarte', 1, 'www.expresarte.com', TO_DATE('2017-04-10','YYYY-MM-DD'), TO_DATE('2017-04-15','YYYY-MM-DD'), 1, 'Monsieur Perine');

CREATE TABLE condicion (
  con_id NUMBER(11) NOT NULL ,
  con_nombre varchar(100) NOT NULL 
);

INSERT INTO condicion (con_id, con_nombre) VALUES(1, 'Amplificacion de sonido');
INSERT INTO condicion (con_id, con_nombre) VALUES(2, 'Efectos de luz');
INSERT INTO condicion (con_id, con_nombre) VALUES(3, 'Fuegos artificiales');
INSERT INTO condicion (con_id, con_nombre) VALUES(4, 'Efectos 4D');
INSERT INTO condicion (con_id, con_nombre) VALUES(5, 'Efectos 3D');
INSERT INTO condicion (con_id, con_nombre) VALUES(6, 'InteracciÛn con el p˙blico');
INSERT INTO condicion (con_id, con_nombre) VALUES(7, 'Efectos en alturas');
INSERT INTO condicion (con_id, con_nombre) VALUES(8, 'InstalaciÛn de escenarios propios');
INSERT INTO condicion (con_id, con_nombre) VALUES(9, 'Instalaciones interactivas');

CREATE TABLE elemento (
  ele_id NUMBER(11) NOT NULL ,
  ele_nombre varchar(100) NOT NULL 
);

INSERT INTO elemento (ele_id, ele_nombre) VALUES(1, 'agua');
INSERT INTO elemento (ele_id, ele_nombre) VALUES(2, 'espumas');
INSERT INTO elemento (ele_id, ele_nombre) VALUES(3, 'vidrios');
INSERT INTO elemento (ele_id, ele_nombre) VALUES(4, 'montajes de escenarios');
INSERT INTO elemento (ele_id, ele_nombre) VALUES(5, 'herramientas');
INSERT INTO elemento (ele_id, ele_nombre) VALUES(6, 'elementos de grandes alturas');
INSERT INTO elemento (ele_id, ele_nombre) VALUES(7, 'efectos visuales o de luces');
INSERT INTO elemento (ele_id, ele_nombre) VALUES(8, 'efectos de sonido o de interacciÛn con el p˙blico');
INSERT INTO elemento (ele_id, ele_nombre) VALUES(9, 'movimiento del p˙blico durante la obra');
INSERT INTO elemento (ele_id, ele_nombre) VALUES(10, 'movimiento del escenario');
INSERT INTO elemento (ele_id, ele_nombre) VALUES(11, 'plataformas intercambiables durante las escenas');

CREATE TABLE espectaculo (
  esp_id NUMBER(11) NOT NULL ,
  esp_nombre varchar(100) NOT NULL ,
  esp_descripcion  varchar(4000) NOT NULL ,
  esp_duracion NUMBER(11) NOT NULL ,
  esp_costo NUMBER(12,2) NOT NULL ,
  idi_id NUMBER(11) NOT NULL ,
  esp_traductor NUMBER(1) NOT NULL ,
  esp_participa_publico NUMBER(1) NOT NULL ,
  puo_id NUMBER(11) NOT NULL ,
  esp_requerimientos varchar(4000) NOT NULL 
);

INSERT INTO espectaculo (esp_id, esp_nombre, esp_descripcion, esp_duracion, esp_costo, idi_id, esp_traductor, esp_participa_publico, puo_id, esp_requerimientos) VALUES(1, 'El coronel no tiene quien le escriba', 'El coronel (su nombre y apellidos nos son desconocidos, pues el autor siempre lo llama "el coronel"), hombre de buena fe y bastante ingenuo, vive en su pueblo esperando recibir el aviso de que le han concedido la pensiÛn a la que tiene derecho por haber servido en su juventud a las Ûrdenes de Aureliano BuendÌa', 2, 10000000, 3, 0, 0, 2, 'Escenario de 12m x 12 m como minimo');
INSERT INTO espectaculo (esp_id, esp_nombre, esp_descripcion, esp_duracion, esp_costo, idi_id, esp_traductor, esp_participa_publico, puo_id, esp_requerimientos) VALUES(2, 'John Onofre', 'PresentaciÛn del mejor cantante de m˙sica llanera y ganador del concurso a otro nivel', 2, 5000000, 3, 0, 0, 2, 'Ninguno diferente a lo indicado en elementos');
INSERT INTO espectaculo (esp_id, esp_nombre, esp_descripcion, esp_duracion, esp_costo, idi_id, esp_traductor, esp_participa_publico, puo_id, esp_requerimientos) VALUES(3, 'La MarÌa', 'MarÌa puede considerarse la obra cumbre de la literatura colombiana rom·ntica, es ejemplo y orgullo que enaltece a las letras colombianas desde el siglo XIX, ha sido traducida amas de 15 idiomas y se han realizado numerosas ediciones de la obra, lo que confirma su importancia capital para la intelectualidad colombiana. ', 2, 25000000, 3, 0, 0, 2, 'Escenario cubierto');
INSERT INTO espectaculo (esp_id, esp_nombre, esp_descripcion, esp_duracion, esp_costo, idi_id, esp_traductor, esp_participa_publico, puo_id, esp_requerimientos) VALUES(4, 'Familia BÈlier', 'En la Familia BÈlier, tres de los miembros (pap·, mam· y un hijo) son sordos. La hija (Louane Emera) , en cambio, tiene una voz preciosa. Ella debe enfrentarse a la dura decisiÛn de dejar a su familia en el campo para seguir su sueÒo de ser cantante en ParÌs. En medio de la trama, las canciones cl·sicas francesas tienen un papel preponderante.', 2, 120000000, 1, 1, 0, 1, 'Escenario 8m x 8m');

CREATE TABLE espectaculo_categoria (
  esc_id NUMBER(11) NOT NULL ,
  esp_id NUMBER(11) NOT NULL ,
  cat_id NUMBER(11) NOT NULL 
);

INSERT INTO espectaculo_categoria (esc_id, esp_id, cat_id) VALUES(1, 1, 2);
INSERT INTO espectaculo_categoria (esc_id, esp_id, cat_id) VALUES(2, 1, 3);
INSERT INTO espectaculo_categoria (esc_id, esp_id, cat_id) VALUES(3, 1, 4);
INSERT INTO espectaculo_categoria (esc_id, esp_id, cat_id) VALUES(4, 1, 5);
INSERT INTO espectaculo_categoria (esc_id, esp_id, cat_id) VALUES(5, 2, 6);
INSERT INTO espectaculo_categoria (esc_id, esp_id, cat_id) VALUES(6, 2, 7);
INSERT INTO espectaculo_categoria (esc_id, esp_id, cat_id) VALUES(7, 3, 4);
INSERT INTO espectaculo_categoria (esc_id, esp_id, cat_id) VALUES(8, 4, 4);
INSERT INTO espectaculo_categoria (esc_id, esp_id, cat_id) VALUES(9, 4, 5);

CREATE TABLE espectaculo_compania (
  esc_id NUMBER(11) NOT NULL ,
  esp_id NUMBER(11) NOT NULL ,
  com_id NUMBER(11) NOT NULL 
);

INSERT INTO espectaculo_compania (esc_id, esp_id, com_id) VALUES(1, 1, 4);
INSERT INTO espectaculo_compania (esc_id, esp_id, com_id) VALUES(2, 2, 3);
INSERT INTO espectaculo_compania (esc_id, esp_id, com_id) VALUES(4, 3, 1);
INSERT INTO espectaculo_compania (esc_id, esp_id, com_id) VALUES(5, 4, 5);

CREATE TABLE espectaculo_elemento (
  ese_id NUMBER(11) NOT NULL ,
  esp_id NUMBER(11) NOT NULL ,
  ele_id NUMBER(11) NOT NULL 
);

INSERT INTO espectaculo_elemento (ese_id, esp_id, ele_id) VALUES(1, 1, 2);
INSERT INTO espectaculo_elemento (ese_id, esp_id, ele_id) VALUES(2, 1, 3);
INSERT INTO espectaculo_elemento (ese_id, esp_id, ele_id) VALUES(3, 1, 4);
INSERT INTO espectaculo_elemento (ese_id, esp_id, ele_id) VALUES(4, 1, 11);
INSERT INTO espectaculo_elemento (ese_id, esp_id, ele_id) VALUES(5, 2, 6);
INSERT INTO espectaculo_elemento (ese_id, esp_id, ele_id) VALUES(6, 2, 7);
INSERT INTO espectaculo_elemento (ese_id, esp_id, ele_id) VALUES(7, 3, 4);
INSERT INTO espectaculo_elemento (ese_id, esp_id, ele_id) VALUES(8, 4, 4);
INSERT INTO espectaculo_elemento (ese_id, esp_id, ele_id) VALUES(9, 4, 9);

CREATE TABLE festival (
  fes_id NUMBER(11) NOT NULL ,
  fes_nombre varchar(100) NOT NULL ,
  fes_fecha_inicio date NOT NULL ,
  fes_fecha_fin date NOT NULL 
);

INSERT INTO festival (fes_id, fes_nombre, fes_fecha_inicio, fes_fecha_fin) VALUES(1, 'Festival de teatro', TO_DATE('2017-04-08','YYYY-MM-DD'), TO_DATE('2017-04-22','YYYY-MM-DD'));
INSERT INTO festival (fes_id, fes_nombre, fes_fecha_inicio, fes_fecha_fin) VALUES(2, 'Feria de las flores', TO_DATE('2017-08-03','YYYY-MM-DD'), TO_DATE('2017-08-08','YYYY-MM-DD'));
INSERT INTO festival (fes_id, fes_nombre, fes_fecha_inicio, fes_fecha_fin) VALUES(3, 'Torneo internacional del joropo', TO_DATE('2017-06-29','YYYY-MM-DD'), TO_DATE('2017-07-09','YYYY-MM-DD'));

CREATE TABLE festival_ciudad (
  fec_id NUMBER(11) NOT NULL ,
  fes_id NUMBER(11) NOT NULL ,
  ciu_id NUMBER(11) NOT NULL 
);

INSERT INTO festival_ciudad (fec_id, fes_id, ciu_id) VALUES(1, 1, 1);
INSERT INTO festival_ciudad (fec_id, fes_id, ciu_id) VALUES(2, 1, 3);
INSERT INTO festival_ciudad (fec_id, fes_id, ciu_id) VALUES(3, 1, 4);
INSERT INTO festival_ciudad (fec_id, fes_id, ciu_id) VALUES(4, 2, 2);
INSERT INTO festival_ciudad (fec_id, fes_id, ciu_id) VALUES(5, 3, 5);

CREATE TABLE funcion (
  fun_id NUMBER(11) NOT NULL ,
  fun_fecha date NOT NULL ,
  fun_realizada NUMBER(1) NOT NULL ,
  sit_id NUMBER(11) NOT NULL ,
  esp_id NUMBER(11) NOT NULL
);

Insert into FUNCION (FUN_ID,FUN_FECHA,FUN_REALIZADA,SIT_ID,ESP_ID) values (1,to_date('25-MAR-17','DD-MON-RR'),1,6,2);
Insert into FUNCION (FUN_ID,FUN_FECHA,FUN_REALIZADA,SIT_ID,ESP_ID) values (2,to_date('27-MAR-17','DD-MON-RR'),0,3,4);
Insert into FUNCION (FUN_ID,FUN_FECHA,FUN_REALIZADA,SIT_ID,ESP_ID) values (3,to_date('12-APR-17','DD-MON-RR'),0,6,2);
Insert into FUNCION (FUN_ID,FUN_FECHA,FUN_REALIZADA,SIT_ID,ESP_ID) values (4,to_date('30-JUN-17','DD-MON-RR'),0,2,3);
Insert into FUNCION (FUN_ID,FUN_FECHA,FUN_REALIZADA,SIT_ID,ESP_ID) values (5,to_date('02-JUL-17','DD-MON-RR'),0,1,1);


CREATE TABLE horario (
  hor_id NUMBER(11) NOT NULL ,
  hor_dia varchar(100) NOT NULL ,
  hor_hora varchar(10) NOT NULL 
);

INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(1, 'Lunes', '16:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(2, 'Lunes', '18:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(3, 'Lunes', '20:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(4, 'Martes', '16:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(5, 'Martes', '18:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(6, 'Martes', '20:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(7, 'MiÈrcoles', '16:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(8, 'MiÈrcoles', '18:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(9, 'MiÈrcoles', '20:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(10, 'Jueves', '16:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(11, 'Jueves', '18:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(12, 'Jueves', '20:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(13, 'Viernes', '16:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(14, 'Viernes', '18:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(15, 'Viernes', '20:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(16, 'S·bado', '16:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(17, 'S·bado', '18:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(18, 'S·bado', '20:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(19, 'Domingo', '16:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(20, 'Domingo', '18:00:00');
INSERT INTO horario (hor_id, hor_dia, hor_hora) VALUES(21, 'Domingo', '20:00:00');

CREATE TABLE idioma (
  idi_id NUMBER(11) NOT NULL ,
  idi_nombre varchar(100) NOT NULL 
);

INSERT INTO idioma (idi_id, idi_nombre) VALUES(1, 'Frances');
INSERT INTO idioma (idi_id, idi_nombre) VALUES(2, 'Holandes');
INSERT INTO idioma (idi_id, idi_nombre) VALUES(3, 'EspaÒol');
INSERT INTO idioma (idi_id, idi_nombre) VALUES(4, 'Ingles');

CREATE TABLE localidad (
  loc_id NUMBER(11) NOT NULL ,
  loc_nombre varchar(100) NOT NULL,
  LOC_CAPACIDAD NUMBER, 
LOC_PRECIO NUMBER
);


INSERT INTO localidad (loc_id, loc_nombre, loc_capacidad, LOC_PRECIO) VALUES(1, 'VIP', 100, 1200);
INSERT INTO localidad (loc_id, loc_nombre, loc_capacidad, LOC_PRECIO) VALUES(2, 'Platea', 200, 300);
INSERT INTO localidad (loc_id, loc_nombre, loc_capacidad, LOC_PRECIO) VALUES(3, 'Platea lateral', 250, 333);
INSERT INTO localidad (loc_id, loc_nombre, loc_capacidad, LOC_PRECIO) VALUES(4, 'Intermedia', 300, 123);
INSERT INTO localidad (loc_id, loc_nombre, loc_capacidad, LOC_PRECIO) VALUES(5, 'Intermedia lateral', 350, 444);
INSERT INTO localidad (loc_id, loc_nombre, loc_capacidad, LOC_PRECIO) VALUES(6, 'General', 1000, 5);



ALTER TABLE LOCALIDAD ADD CONSTRAINT LOCALIDAD_CHK1 CHECK (LOC_PRECIO > 0);



--integridad check

--Inserte tuplas que cumplen con las restricciones de chequeo establecidas 


INSERT INTO localidad (loc_id, loc_nombre, loc_capacidad, LOC_PRECIO) VALUES(7, 'Platino', 1111, 15);



--Inserte tuplas que violan las restricciones de chequeo establecidas


INSERT INTO localidad (loc_id, loc_nombre, loc_capacidad, LOC_PRECIO) VALUES(8, 'Luneta', 1111, -15);


--Haga las pruebas de inserción y borrado correspondientes.


DELETE FROM LOCALIDAD WHERE LOC_ID = 8;


CREATE TABLE necesidad (
  nec_id NUMBER(11) NOT NULL ,
  nec_nombre varchar(100) NOT NULL 
);

INSERT INTO necesidad (nec_id, nec_nombre) VALUES(1, 'Menores de edad');
INSERT INTO necesidad (nec_id, nec_nombre) VALUES(2, 'Restricciones de movilidad');
INSERT INTO necesidad (nec_id, nec_nombre) VALUES(3, 'Personas mayores');

CREATE TABLE pais (
  pai_id NUMBER(11) NOT NULL ,
  pai_nombre varchar(50) NOT NULL 
);

INSERT INTO pais (pai_id, pai_nombre) VALUES(1, 'Francia');
INSERT INTO pais (pai_id, pai_nombre) VALUES(2, 'Holanda');
INSERT INTO pais (pai_id, pai_nombre) VALUES(3, 'Venezuela');
INSERT INTO pais (pai_id, pai_nombre) VALUES(4, 'EspaÒa');
INSERT INTO pais (pai_id, pai_nombre) VALUES(5, 'Mexico');
INSERT INTO pais (pai_id, pai_nombre) VALUES(6, 'Colombia');

CREATE TABLE preferencia (
  pre_id NUMBER(11) NOT NULL ,
  pre_nombre varchar(50) NOT NULL 
);

INSERT INTO preferencia (pre_id, pre_nombre) VALUES(1, 'Ninos');
INSERT INTO preferencia (pre_id, pre_nombre) VALUES(2, 'Jovenes');
INSERT INTO preferencia (pre_id, pre_nombre) VALUES(3, 'Adultos');
INSERT INTO preferencia (pre_id, pre_nombre) VALUES(4, 'Coliseo Alvaro Mesa');
INSERT INTO preferencia (pre_id, pre_nombre) VALUES(5, 'Danzarte');

CREATE TABLE publico_objetivo (
  puo_id NUMBER(11) NOT NULL ,
  puo_nombre varchar(50) NOT NULL 
);

INSERT INTO publico_objetivo (puo_id, puo_nombre) VALUES(1, 'Adultos');
INSERT INTO publico_objetivo (puo_id, puo_nombre) VALUES(2, 'Familiar');
INSERT INTO publico_objetivo (puo_id, puo_nombre) VALUES(3, 'Infantil');
INSERT INTO publico_objetivo (puo_id, puo_nombre) VALUES(4, 'JÛvenes');

CREATE TABLE rol (
  rol_id NUMBER(11) NOT NULL ,
  rol_nombre varchar(100) NOT NULL 
);

INSERT INTO rol (rol_id, rol_nombre) VALUES(1, 'Organizador');
INSERT INTO rol (rol_id, rol_nombre) VALUES(2, 'Cliente');
INSERT INTO rol (rol_id, rol_nombre) VALUES(3, 'Logistica');
INSERT INTO rol (rol_id, rol_nombre) VALUES(4, 'CompaÒia');

CREATE TABLE TIPO_SILLA (
  sil_id NUMBER(11) NOT NULL ,
  con_nombre varchar(100) NOT NULL 
);

INSERT INTO tipo_silla (sil_id, con_nombre) VALUES(1, 'MÛvil');
INSERT INTO tipo_silla (sil_id, con_nombre) VALUES(2, 'Fija');
INSERT INTO tipo_silla (sil_id, con_nombre) VALUES(3, 'Removible');

CREATE TABLE sitio (
  sit_id NUMBER(11) NOT NULL ,
  sit_nombre varchar(100) NOT NULL ,
  sit_abierto NUMBER(1) NOT NULL ,
  sit_capacidad NUMBER(11) NOT NULL ,
  ciu_id NUMBER(11) NOT NULL ,
  sil_id NUMBER(11) NOT NULL ,
  sit_proteccion_lluvia NUMBER(1) NOT NULL 
);

INSERT INTO sitio (sit_id, sit_nombre, sit_abierto, sit_capacidad, ciu_id, sil_id, sit_proteccion_lluvia) VALUES(1, 'Teatro Jorge Eliecer Gaitan', 0, 500, 1, 1, 1);
INSERT INTO sitio (sit_id, sit_nombre, sit_abierto, sit_capacidad, ciu_id, sil_id, sit_proteccion_lluvia) VALUES(2, 'Teatro La Castellana', 0, 400, 1, 2, 1);
INSERT INTO sitio (sit_id, sit_nombre, sit_abierto, sit_capacidad, ciu_id, sil_id, sit_proteccion_lluvia) VALUES(3, 'Teatro Metropolitano', 0, 450, 2, 1, 1);
INSERT INTO sitio (sit_id, sit_nombre, sit_abierto, sit_capacidad, ciu_id, sil_id, sit_proteccion_lluvia) VALUES(4, 'Teatro Jorge Isaacs', 0, 425, 3, 2, 1);
INSERT INTO sitio (sit_id, sit_nombre, sit_abierto, sit_capacidad, ciu_id, sil_id, sit_proteccion_lluvia) VALUES(5, 'Teatro La Sala', 0, 525, 4, 1, 1);
INSERT INTO sitio (sit_id, sit_nombre, sit_abierto, sit_capacidad, ciu_id, sil_id, sit_proteccion_lluvia) VALUES(6, 'Coliseo Alvaro Mesa', 1, 1500, 5, 2, 1);
INSERT INTO sitio (sit_id, sit_nombre, sit_abierto, sit_capacidad, ciu_id, sil_id, sit_proteccion_lluvia) VALUES(7, 'Coliseo Los Fundadores', 1, 2500, 5, 3, 0);

CREATE TABLE sitio_condicion (
  sin_id NUMBER(11) NOT NULL ,
  sit_id NUMBER(11) NOT NULL ,
  con_id NUMBER(11) NOT NULL 
);

CREATE TABLE sitio_horario (
  sih_id NUMBER(11) NOT NULL ,
  sit_id NUMBER(11) NOT NULL ,
  hor_id NUMBER(11) NOT NULL 
);

CREATE TABLE sitio_localidad (
  stl_id NUMBER(11) NOT NULL ,
  sit_id NUMBER(11) NOT NULL ,
  loc_id NUMBER(11) NOT NULL 
);

Insert into SITIO_LOCALIDAD (STL_ID,SIT_ID,LOC_ID) values (1,6,1);
Insert into SITIO_LOCALIDAD (STL_ID,SIT_ID,LOC_ID) values (2,2,1);
Insert into SITIO_LOCALIDAD (STL_ID,SIT_ID,LOC_ID) values (3,5,2);
Insert into SITIO_LOCALIDAD (STL_ID,SIT_ID,LOC_ID) values (4,7,3);
Insert into SITIO_LOCALIDAD (STL_ID,SIT_ID,LOC_ID) values (5,3,4);
Insert into SITIO_LOCALIDAD (STL_ID,SIT_ID,LOC_ID) values (6,1,5);

CREATE TABLE sitio_necesidad (
  sit_id NUMBER(11) NOT NULL ,
  nec_id NUMBER(11) NOT NULL 
);

Insert into SITIO_NECESIDAD (SIT_ID,NEC_ID) values (1,2);
Insert into SITIO_NECESIDAD (SIT_ID,NEC_ID) values (2,3);
Insert into SITIO_NECESIDAD (SIT_ID,NEC_ID) values (3,1);

--caso prueba tuplas sin integridad "A foreign key value has no matching primary key value"
Insert into SITIO_NECESIDAD (SIT_ID,NEC_ID) values (4,3);
Insert into SITIO_NECESIDAD (SIT_ID,NEC_ID) values (5,2);
Insert into SITIO_NECESIDAD (SIT_ID,NEC_ID) values (6,3);



CREATE TABLE usuario (
  usu_id NUMBER(11) NOT NULL ,
  usu_identificacion NUMBER(11) NOT NULL ,
  usu_nombre varchar(100) NOT NULL ,
  usu_contrasena varchar(10) NOT NULL ,
  usu_correo varchar(100) NOT NULL ,
  rol_id NUMBER(11) NOT NULL 
);

INSERT INTO usuario (usu_id, usu_identificacion, usu_nombre, usu_contrasena, usu_correo, rol_id) VALUES(1, 21215757, 'Juan Pedro Gonzalez', 'Juan123*', 'juan.gonzalez@hotmail.com', 1);
INSERT INTO usuario (usu_id, usu_identificacion, usu_nombre, usu_contrasena, usu_correo, rol_id) VALUES(2, 40375137, 'Juanita Uricoechea', 'Urico123*', 'juanitauri@gmail.com', 3);
Insert into USUARIO (USU_ID,USU_IDENTIFICACION,USU_NOMBRE,USU_CONTRASENA,USU_CORREO,ROL_ID) values (11,21215752,'Daniela Gamba','Gamba123','gamba@hotmail.com',2);
Insert into USUARIO (USU_ID,USU_IDENTIFICACION,USU_NOMBRE,USU_CONTRASENA,USU_CORREO,ROL_ID) values (3,21215758,'Maria Leon','Maria123*','marileon@hotmail.com',1);
Insert into USUARIO (USU_ID,USU_IDENTIFICACION,USU_NOMBRE,USU_CONTRASENA,USU_CORREO,ROL_ID) values (4,40375138,'Nicolas Acevedo','Nicox123*','nicoa@gmail.com',1);
Insert into USUARIO (USU_ID,USU_IDENTIFICACION,USU_NOMBRE,USU_CONTRASENA,USU_CORREO,ROL_ID) values (5,21215759,'Camilo Montenegro','Cami123*','camimont@hotmail.com',2);
Insert into USUARIO (USU_ID,USU_IDENTIFICACION,USU_NOMBRE,USU_CONTRASENA,USU_CORREO,ROL_ID) values (6,40375139,'Daniela Jaimes','Dani123','danidani@gmail.com',3);
Insert into USUARIO (USU_ID,USU_IDENTIFICACION,USU_NOMBRE,USU_CONTRASENA,USU_CORREO,ROL_ID) values (7,21215750,'Bibiana Gamba','Bibux123*','bibianag@hotmail.com',1);
Insert into USUARIO (USU_ID,USU_IDENTIFICACION,USU_NOMBRE,USU_CONTRASENA,USU_CORREO,ROL_ID) values (8,40375130,'German Bravo','Germu123*','meamogerman@gmail.com',3);
Insert into USUARIO (USU_ID,USU_IDENTIFICACION,USU_NOMBRE,USU_CONTRASENA,USU_CORREO,ROL_ID) values (9,21215751,'Claudia Jimenez','Clau123*','claudia@hotmail.com',1);
Insert into USUARIO (USU_ID,USU_IDENTIFICACION,USU_NOMBRE,USU_CONTRASENA,USU_CORREO,ROL_ID) values (10,40375141,'Ramon Vega','Rami123*','ramoncito@gmail.com',2);
Insert into USUARIO (USU_ID,USU_IDENTIFICACION,USU_NOMBRE,USU_CONTRASENA,USU_CORREO,ROL_ID) values (12,40375132,'Olga Lucia Sabogal','Olga123*','olgalu@gmail.com',1);


CREATE TABLE usuario_preferencia (
  usu_id NUMBER(11) NOT NULL ,
  pre_id NUMBER(11) NOT NULL 
);

  

CREATE TABLE SILLA_PAGADA
   (	PAG_ID NUMBER(10,0) NOT NULL ENABLE, 
	PAG_FILA VARCHAR2(1 BYTE), 
	PAG_COLUMNA NUMBER(10,0), 
	PAG_FECHA_PAGO NUMBER NOT NULL ENABLE, 
	USU_ID NUMBER(10,0), 
FUN_ID NUMBER(10,0),
LOC_ID NUMBER(10,0)
	 )
;

ALTER TABLE SILLA_PAGADA ADD PRIMARY KEY (PAG_ID);

ALTER TABLE SILLA_PAGADA ADD (CONSTRAINT USUARIO_FK8 FOREIGN KEY (USU_ID) REFERENCES  USUARIO (USU_ID));

ALTER TABLE SILLA_PAGADA ADD CONSTRAINT FUNCION_FK8 FOREIGN KEY (FUN_ID) REFERENCES FUNCION (FUN_ID) ENABLE;

ALTER TABLE SILLA_PAGADA ADD CONSTRAINT LOCALIDAD_FK8 FOREIGN KEY (LOC_ID) REFERENCES LOCALIDAD (LOC_ID) ENABLE;




ALTER TABLE categoria
  ADD PRIMARY KEY (cat_id);

ALTER TABLE ciudad
  ADD PRIMARY KEY (ciu_id);

ALTER TABLE compania
  ADD PRIMARY KEY (com_id);
 
 ALTER TABLE condicion
   ADD PRIMARY KEY (con_id);
 
 ALTER TABLE elemento
   ADD PRIMARY KEY (ele_id);
 
 ALTER TABLE espectaculo
  ADD PRIMARY KEY (esp_id);
  
 ALTER TABLE espectaculo_categoria
  ADD PRIMARY KEY (esc_id);
  
  ALTER TABLE espectaculo_elemento
  ADD PRIMARY KEY (ese_id);
  
  ALTER TABLE espectaculo_compania
  ADD PRIMARY KEY (esc_id);
  
 ALTER TABLE festival
   ADD PRIMARY KEY (fes_id);

 ALTER TABLE FESTIVAL 
   ADD CHECK (FES_FECHA_INICIO < FES_FECHA_FIN);
 
 ALTER TABLE festival_ciudad
  ADD PRIMARY KEY (fec_id);
  
  ALTER TABLE horario
    ADD PRIMARY KEY (hor_id);
  
  ALTER TABLE idioma
    ADD PRIMARY KEY (idi_id);
  
  ALTER TABLE localidad
    ADD PRIMARY KEY (loc_id);
  
  ALTER TABLE necesidad
    ADD PRIMARY KEY (nec_id);
  
  ALTER TABLE pais
    ADD PRIMARY KEY (pai_id);
  
  ALTER TABLE preferencia
    ADD PRIMARY KEY (pre_id);
  
  ALTER TABLE publico_objetivo
    ADD PRIMARY KEY (puo_id);
  
  ALTER TABLE rol
    ADD PRIMARY KEY (rol_id);
  
  ALTER TABLE tipo_silla
    ADD PRIMARY KEY (sil_id);
    
  ALTER TABLE sitio
  ADD PRIMARY KEY (sit_id);
  
  ALTER TABLE funcion
  ADD PRIMARY KEY (fun_id);
  
  ALTER TABLE sitio_condicion
  ADD PRIMARY KEY (sin_id);
  
  ALTER TABLE sitio_horario
  ADD PRIMARY KEY (sih_id);
  
  ALTER TABLE sitio_localidad
  ADD PRIMARY KEY (stl_id);
  
  ALTER TABLE sitio_necesidad
  ADD PRIMARY KEY ("SIT_ID", "NEC_ID");
  
  
  ALTER TABLE usuario
  ADD PRIMARY KEY (usu_id);
  
  ALTER TABLE usuario_preferencia
    ADD PRIMARY KEY (usu_id, pre_id);

 ALTER TABLE compania ADD (
  CONSTRAINT festival_FK
 FOREIGN KEY (fes_id) 
 REFERENCES festival (fes_id));

ALTER TABLE compania ADD (
  CONSTRAINT pais_FK
 FOREIGN KEY (pai_id) 
 REFERENCES pais (pai_id));
 
ALTER TABLE espectaculo ADD (CONSTRAINT FK_IDIOMA FOREIGN KEY (idi_id) REFERENCES idioma (idi_id));
ALTER TABLE espectaculo ADD (CONSTRAINT FK_publico_objetivo FOREIGN KEY (puo_id) REFERENCES publico_objetivo (puo_id));


 
ALTER TABLE espectaculo_categoria ADD (
  CONSTRAINT FK_ESPECTACULO_5 FOREIGN KEY (esp_id) REFERENCES espectaculo (esp_id));
 
ALTER TABLE espectaculo_categoria ADD (
  CONSTRAINT FK_CATEGORIA_5 FOREIGN KEY (cat_id) REFERENCES categoria (cat_id));
  


 ALTER TABLE espectaculo_compania ADD (CONSTRAINT FK_ESPECTACULO_0 FOREIGN KEY (esp_id) REFERENCES espectaculo (esp_id));
 ALTER TABLE espectaculo_compania ADD (CONSTRAINT FK_COMPANIA_3 FOREIGN KEY (com_id) REFERENCES compania (com_id));

 
  ALTER TABLE espectaculo_elemento ADD CONSTRAINT UK_ESPECTACULO_ELEMENTO UNIQUE (esp_id,ele_id);
  ALTER TABLE espectaculo_elemento ADD (CONSTRAINT FK_ESPECTACULO_3 FOREIGN KEY(esp_id) REFERENCES espectaculo (esp_id));
  ALTER TABLE espectaculo_elemento ADD (CONSTRAINT FK_ELEMENTO_3 FOREIGN KEY(ele_id) REFERENCES elemento (ele_id));
  

 
  ALTER TABLE festival_ciudad ADD (CONSTRAINT fk_festival FOREIGN KEY(fes_id) REFERENCES festival (fes_id));
  ALTER TABLE festival_ciudad ADD (CONSTRAINT fk_ciudad FOREIGN KEY(ciu_id) REFERENCES ciudad (ciu_id));
  


  
ALTER TABLE funcion ADD (CONSTRAINT FK_SITIO FOREIGN KEY(sit_id) REFERENCES sitio (sit_id));
ALTER TABLE funcion ADD (CONSTRAINT FK_ESPECTACULO FOREIGN KEY(esp_id) REFERENCES espectaculo (esp_id));
  


ALTER TABLE sitio ADD (CONSTRAINT FK_SILLA_1 FOREIGN KEY(sil_id) REFERENCES tipo_silla (sil_id));
ALTER TABLE sitio ADD (CONSTRAINT FK_CIUDAD_1 FOREIGN KEY(ciu_id) REFERENCES ciudad (ciu_id));




ALTER TABLE sitio_condicion ADD (CONSTRAINT FK_SITIO_8 FOREIGN KEY(sit_id) REFERENCES sitio (sit_id));
ALTER TABLE sitio_condicion ADD (CONSTRAINT FK_CONDICION_8 FOREIGN KEY(con_id) REFERENCES condicion (con_id));



ALTER TABLE sitio_horario ADD (CONSTRAINT FK_SITIO_2 FOREIGN KEY(sit_id) REFERENCES sitio (sit_id));
ALTER TABLE sitio_horario ADD (CONSTRAINT FK_HORARIO_2 FOREIGN KEY(hor_id) REFERENCES horario (hor_id));




ALTER TABLE sitio_localidad ADD (CONSTRAINT FK_SITIO_10 FOREIGN KEY(sit_id) REFERENCES sitio (sit_id));
ALTER TABLE sitio_localidad ADD (CONSTRAINT FK_localidad_10 FOREIGN KEY(loc_id) REFERENCES localidad (loc_id));



ALTER TABLE sitio_necesidad ADD (CONSTRAINT FK_SITIO_6 FOREIGN KEY(sit_id) REFERENCES sitio (sit_id));
ALTER TABLE sitio_necesidad ADD (CONSTRAINT FK_NECESIDAD_6 FOREIGN KEY(NEC_id) REFERENCES necesidad (nec_id));


  
ALTER TABLE usuario ADD (CONSTRAINT FK_ROL FOREIGN KEY(rol_id) REFERENCES rol(rol_id));



ALTER TABLE usuario_preferencia ADD (CONSTRAINT pre_id FOREIGN KEY(pre_id) REFERENCES preferencia (pre_id));
ALTER TABLE usuario_preferencia ADD (CONSTRAINT usu_id FOREIGN KEY(usu_id) REFERENCES usuario (usu_id));



CREATE SEQUENCE Sq_Sq_categoria
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_ciudad
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_compania
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_condicion
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_espectaculo
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_espectaculo_categoria
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_espectaculo_compania
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_espectaculo_elemento
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_festival
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_festival_ciudad
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_funcion
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_horario
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_idioma
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_localidad
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_necesidad
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_pais
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_preferencia
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_publico_objetivo
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_role
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_tipo_silla
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_sitio
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_sitio_condicion
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_sitio_horario
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_localidad_2
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_sitio_necesidad
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_usuario
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


CREATE SEQUENCE Sq_usuario_preferencia
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;

CREATE SEQUENCE Sq_silla_pagada
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  ORDER;


--integridad GENERAL



INSERT INTO festival (fes_id, fes_nombre, fes_fecha_inicio, fes_fecha_fin) VALUES(5, 'Festival de comedia', TO_DATE('2017-05-08','YYYY-MM-DD'), TO_DATE('2017-05-22','YYYY-MM-DD'));


INSERT INTO ciudad (ciu_id, ciu_nombre) VALUES(6, 'Neiva');



--Inserte una tupla1 que tenga una FK que se encuentra en la tabla referenciada


INSERT INTO festival_ciudad (fec_id, fes_id, ciu_id) VALUES(10, 5, 6);


--Inserte una tupla1 que tenga una FK que no se encuentra en la tabla referenciada


INSERT INTO festival_ciudad (fec_id, fes_id, ciu_id) VALUES(11, 10, 6);




--integridad GENERAL


INSERT INTO festival (fes_id, fes_nombre, fes_fecha_inicio, fes_fecha_fin) VALUES(5, 'Festival de comedia', TO_DATE('2017-05-08','YYYY-MM-DD'), TO_DATE('2017-05-22','YYYY-MM-DD'));



INSERT INTO ciudad (ciu_id, ciu_nombre) VALUES(6, 'Neiva');



--Inserte una tupla1 que tenga una FK que se encuentra en la tabla referenciada


INSERT INTO festival_ciudad (fec_id, fes_id, ciu_id) VALUES(10, 5, 6);
--Inserte una tupla1 que tenga una FK que no se encuentra en la tabla referenciada

INSERT INTO festival_ciudad (fec_id, fes_id, ciu_id) VALUES(11, 10, 6);


--BORRADO
--Haga pruebas de borrado de tuplas maestras y dependientes.
DELETE FROM FESTIVAL WHERE FES_ID = 5;
DELETE FROM FESTIVAL_CIUDAD WHERE FEC_ID = 10;



