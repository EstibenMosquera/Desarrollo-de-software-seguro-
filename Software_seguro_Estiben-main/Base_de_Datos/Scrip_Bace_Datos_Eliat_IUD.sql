/*CREACIÓN DE LA BASE DE DATOS*/
CREATE database IUD_ELIATH_Funcionarios;

USE IUD_ELIATH_Funcionarios;

/*CREACIÓN DE LAS TABLAS*/
CREATE TABLE tipos_identificaciones(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(100),
PRIMARY KEY (id));

CREATE TABLE estados_civiles(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(100),
PRIMARY KEY (id));

CREATE TABLE funcionarios(
id INT NOT NULL AUTO_INCREMENT,
numero_identificacion VARCHAR(50) NOT NULL UNIQUE,
nombres VARCHAR(100) NOT NULL,
apellidos VARCHAR(100) NOT NULL,
sexo VARCHAR(45),
direccion VARCHAR(100) NOT NULL,
telefono VARCHAR(100),
fecha_nacimiento DATE,
fecha_creacion DATETIME DEFAULT NOW(),
fecha_actualizacion DATETIME DEFAULT NOW(),
estados_civiles_id INT NOT NULL,
tipos_identificaciones_id INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (estados_civiles_id) REFERENCES estados_civiles(id),
FOREIGN KEY (tipos_identificaciones_id) REFERENCES tipos_identificaciones(id)
);

CREATE TABLE niveles_educativos(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(100) NOT NULL,
PRIMARY KEY(id));

CREATE TABLE universidades(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(150) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE estados(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(100),
PRIMARY KEY(id));

CREATE TABLE formaciones_academicas(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(150) NOT NULL,
niveles_educativos_id INT NOT NULL,
universidades_id INT NOT NULL,
estados_id INT NOT NULL,
funcionarios_id INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (funcionarios_id) REFERENCES funcionarios(id),
FOREIGN KEY (estados_id) REFERENCES estados(id),
FOREIGN KEY (universidades_id) REFERENCES universidades(id),
FOREIGN KEY (niveles_educativos_id) REFERENCES niveles_educativos(id));

CREATE TABLE parentescos(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE grupos_familiares(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(100) NOT NULL,
funcionarios_id INT NOT NULL,
parentescos_id INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (funcionarios_id) REFERENCES funcionarios(id),
FOREIGN KEY (parentescos_id) REFERENCES parentescos(id));

/*INSERCIÓN DE DATOS*/

/*TABLA TIPOS DE IDENTIFICACIÓN*/
INSERT INTO tipos_identificaciones(nombre, descripcion)
VALUES('CC', 'Cédula De Ciudadanía');
INSERT INTO tipos_identificaciones(nombre, descripcion)
VALUES('RC', 'Registro Civil');
INSERT INTO tipos_identificaciones(nombre, descripcion)
VALUES('TI', 'Tarjeta De Identidad');
INSERT INTO tipos_identificaciones(nombre, descripcion)
VALUES('CE', 'Cédula De Extranjería');
INSERT INTO tipos_identificaciones(nombre, descripcion)
VALUES('PP', 'Pasaporte');

/*TABLA ESTADOS CIVILES*/
INSERT INTO estados_civiles(nombre)
VALUES('Casado');
INSERT INTO estados_civiles(nombre)
VALUES('Soltero');
INSERT INTO estados_civiles(nombre)
VALUES('Comprometido');
INSERT INTO estados_civiles(nombre)
VALUES('Prefiero No Decirlo');

/*TABLA ESTADOS*/
INSERT INTO estados(nombre)
VALUES('En Proceso');
INSERT INTO estados(nombre)
VALUES('Finalizado');
INSERT INTO estados(nombre)
VALUES('Suspendido');

/*TABLA PARENTESCOS*/
INSERT INTO parentescos(nombre)
VALUES('Primo/a');
INSERT INTO parentescos(nombre)
VALUES('Hermano/a');
INSERT INTO parentescos(nombre)
VALUES('Madre');
INSERT INTO parentescos(nombre)
VALUES('Padre');
INSERT INTO parentescos(nombre)
VALUES('Tio/a');
INSERT INTO parentescos(nombre)
VALUES('Sobrino/a');
INSERT INTO parentescos(nombre)
VALUES('Abuelo/a');

/*TABLA NIVELES ACADÉMICOS*/
INSERT INTO niveles_educativos(nombre)
VALUES('Bachiller Académico');
INSERT INTO niveles_educativos(nombre)
VALUES('Estudios Básicos No Finalizados');
INSERT INTO niveles_educativos(nombre)
VALUES('Técnica');
INSERT INTO niveles_educativos(nombre)
VALUES('Tecnología');
INSERT INTO niveles_educativos(nombre)
VALUES('Pregrado');
INSERT INTO niveles_educativos(nombre)
VALUES('Posgradro');
INSERT INTO niveles_educativos(nombre)
VALUES('Maestría');
INSERT INTO niveles_educativos(nombre)
VALUES('Doctorado');

/*TABLA FUNCIONARIOS*/
INSERT INTO funcionarios(numero_identificacion,nombres,apellidos,sexo,direccion,telefono,fecha_nacimiento,
estados_civiles_id,tipos_identificaciones_id)
VALUES('1001176897','Sofia','Hoyos','FEMENINO','Calle 33 #45_A_27','3205654773','1998-07-22',3,1);
