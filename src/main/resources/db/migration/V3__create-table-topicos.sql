create table topicos (
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(200) not null,
    fecha_creacion datetime not null,
    estado varchar(30),
    usuario_id bigint not null,
    curso_id bigint not null,
    primary key (id),
    constraint fk_topico_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_topico_curso_id foreign key(curso_id) references cursos(id)
);