create table usuarios (
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    telefono varchar(20) not null,
    usuario varchar(100) not null unique,
    clave varchar(100) not null,
    activo tinyint not null,
    rol varchar(20) not null,
    primary key (id)
);