CREATE DATABASE SistemaControl;

USE SistemaControl;

drop table if exists administrador;
drop table if exists trabajador;
drop table if exists Usuario;
drop table if exists productos;
drop table if exists proveedor;

CREATE TABLE Usuario (
	ID_Usuario SERIAL PRIMARY KEY,
	USERNAME varchar(50),
	CONTRASENIA varchar(120),
	created_at TIMESTAMP NULL,
	updated_at TIMESTAMP NULL
);


CREATE TABLE Trabajador (
	ID_TRAB SERIAL PRIMARY KEY,
    ID_Usuario int,
	NOMBRE VARCHAR(250),
	APELLIDO VARCHAR(250),
	DNI VARCHAR(8) UNIQUE,
	DIRECCION VARCHAR(50),
	CELULAR VARCHAR(50),
	FECHA_NACIMIENTO DATE,
	created_at TIMESTAMP NULL,
	updated_at TIMESTAMP NULL,
    FOREIGN KEY(ID_Usuario) references Usuario(ID_Usuario)
);

-- Tabla del administrador
CREATE TABLE administrador(
	ID_Admin SERIAL PRIMARY KEY,
	ID_Usuario int,
	NOMBRE VARCHAR(250),
	APELLIDO VARCHAR(250),
	DNI VARCHAR(8),
	DIRECCION VARCHAR(50),
	CELULAR VARCHAR(50),
	FECHA_NACIMIENTO DATE,
	created_at TIMESTAMP NULL,
	updated_at TIMESTAMP NULL,
	FOREIGN KEY(ID_Usuario) references Usuario(ID_Usuario)
);



-- Tabla de proveedor
CREATE TABLE proveedor(
	ID_PROVEEDOR SERIAL PRIMARY KEY,
	NOMBRE VARCHAR(250),
	RUC VARCHAR(50),
	DIRECCION VARCHAR(100),
	CELULAR VARCHAR(50),
	created_at TIMESTAMP NULL,
	updated_at TIMESTAMP NULL
);


-- Tabla de producto
CREATE TABLE productos(
	ID_PROD SERIAL PRIMARY KEY,
    ID_PROVEEDOR int ,
	DESCRIPCION VARCHAR(250),
	CANTIDAD int,
	PRECIO decimal(8,2),
	IMAGEN oid NULL,
	created_at TIMESTAMP NULL,
	updated_at TIMESTAMP NULL,
    FOREIGN KEY(ID_PROVEEDOR) references Proveedor(ID_PROVEEDOR)
);



insert into proveedor(NOMBRE,RUC,DIRECCION,CELULAR,created_at) values('Marcas Neste SA de C.V' , '102541256987' , 'Lima' , '985632154' ,current_timestamp );
insert into proveedor(NOMBRE,RUC,DIRECCION,CELULAR,created_at) values('Sigma Alimentos Comercial SA de C.V' , '114256321000' , 'Lima' , '954123658' ,current_timestamp );

insert into productos(ID_PROVEEDOR,DESCRIPCION,CANTIDAD,PRECIO,created_at) values(1 , 'Leche Nestle' , 120 , 3.5 ,current_timestamp );
insert into productos(ID_PROVEEDOR,DESCRIPCION,CANTIDAD,PRECIO,created_at) values(2 , 'Arroz Costeño' , 60 , 3.7 ,current_timestamp );

insert into Usuario(USERNAME,CONTRASENIA,created_at) values('admin' , '123456' , current_timestamp);
insert into Usuario(USERNAME,CONTRASENIA,created_at) values('luis12' , '123456' , current_timestamp);
insert into Usuario(USERNAME,CONTRASENIA,created_at) values('carlos12' , '123456' , current_timestamp);
insert into Usuario(USERNAME,CONTRASENIA,created_at) values('manuel12' , '123456' , current_timestamp);

insert into administrador(ID_Usuario,NOMBRE,APELLIDO,DNI,DIRECCION,CELULAR,FECHA_NACIMIENTO,created_at) 
values(1 , 'Sergio' ,'Sanchez Cardenas' , '45236412' ,'Chorrillos','954125636','2000-12-11' ,current_timestamp);
insert into administrador(ID_Usuario,NOMBRE,APELLIDO,DNI,DIRECCION,CELULAR,FECHA_NACIMIENTO,created_at) 
values(2 , 'Luis' ,'Alarcon Fernandez' , '41021000' ,'San Juan de Miraflores','965211236','1999-03-04' ,current_timestamp);

insert into Trabajador(ID_Usuario,NOMBRE,APELLIDO,DNI,DIRECCION,CELULAR,FECHA_NACIMIENTO,created_at) 
values(3 , 'Carlos' ,'Dulanto Salvatierra' , '41025632' ,'San Juan de Miraflores','954789652','1998-11-25' ,current_timestamp);
insert into Trabajador(ID_Usuario,NOMBRE,APELLIDO,DNI,DIRECCION,CELULAR,FECHA_NACIMIENTO,created_at) 
values(4 , 'Manuel' ,'Dulan Paucar' , '15236548' ,'Villa El Salvador','965236985','1995-06-04' ,current_timestamp);

select * from Usuario;
select * from proveedor;
select * from productos;
select * from Trabajador;
select * from administrador;