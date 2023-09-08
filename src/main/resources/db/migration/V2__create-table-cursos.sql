create table cursos (
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    descripcion varchar(200) not null,
    duracion varchar(30),
    lenguaje varchar(30),
    primary key (id)
);