# ForoAlura - Challenge Spring Boot
## Formacion Backend Oracle Next Education
![logo_hotel](https://github.com/AndresFonseca132/ForoAlura/assets/125603660/fe6a7512-4c19-4eec-b336-7c82f4104e7c)

## Descripcion del Proyecto
Este es un proyecto desarrollado en Java con las tecnologias de Spring Boot y MySQL, que consiste una API rest del funcionamiento de un Foro, este Proyecto es un reto propuesto por alura para poder aplicar mis conocimientos adquiridos en Java Y SpringBoot y
asi poder plasmarlos ponerlos en practica en una Aplicacion real.

`Spring`, `MySQL`, `IntelliJ Idea`
## Desarrollo
Para el desarrollo de este proyecto se real primero se realizo un analisis de los requisitos propuestos por Alura, los cuales ellos nos brindaron mediante un video explicativo y un tablero en la aplicacion Trello para asi poder desarrollar el proyecto.
Durante el desarrollo de este proyecto realice varios analisis antes de empezar en donde primero construi un modelo Entidad Relacion de la base de datos que iba a usar, ademas realice algunas modificiones al modelo de bases de datos para poder agregar nuevas
funcionalidades como tabla de respuesta, tabla de usuarios en donde valido la autenticacion, las tablas de cursos y respuestas. Finalmente para poder evidenciar nuestros resultados y validar que todo funcionara hicimos uso de un cliente como lo es `postman`
desde el cual enviaba mis request y observaba los resultados.
## Estructura
La organizacion del proyecto se compone de varias carpetas y pavkages que almacenan las clases y los archivos que permiten su funcionamiento, su estructura va de la siguiente manera:
### `controller`
La carpeta controller contiene las clases que se encargan del flujo de peticiones que se realicen 
### `domain`
El packeage de domain contiene dentro de si varios sub packages que contienen lo que son los modelos que se convertiran en entidades
ademas de los DTO para la recepcion y envio de datos.
### `infra`
Finalmente el package infra contiene lo que es la logica para el tratramiento de errores, la documentacion con Spring Swagger y las clases que se encargan del tema de autenticacion de usuarios con la generacion del token
## Base de Datos:
![image](https://github.com/AndresFonseca132/ForoAlura/assets/125603660/ffcee73c-5c96-4888-bb38-959bea25949a) 

El modelo de base de datos se conforma por 4 tablas las cuales son `cursos`, `topicos`, `usuarios` y `respuestas`, estas tablas estan conectada entre se por medio de relaciones las cuales en su totalidad son de uno a muchos.
## EndPoints
### Cursos
- /cursos [POST] - Registrar Curso
- /cursos [GET] - Listar todos los cursos
- /cursos/{id} [GET] - Obtener un curso por ID
- /cursos/{id} [PUT] - Editar un curso de acuerdo al Id que pase por la url
- /cursos/{id} [DELETE] - Eliminar un curso por el Id en la url
- /curso/lenguaje/{lenguaje} - Obtener los cursos de acuerdo al lenguaje
### Usuarios
- /usuarios [POST] - Registrar un usuario
- /usuarios [GET] - Listar todos los usuarios
- /usuarios/{id} [PUT] - Editar al usuario por el id en la url
- /usuarios/{id} [DELETE] - Eliminar el usuario por el id en la url
- /usuarios/{id} [GET] - Obtener un usuario por su id
### Topicos
- /topicos [POST] - Registrar un nuevo topico
- /topicos [GET] - Listar todos los topicos
- /topicos/{id} [PUT] - Editar el topico por su id en la url
- /topicos/{id} [DELETE] - Elimina un topico por su id
- /topicos/{id} [GET] - Obtener un topico especifico por su id
### Respuestas
- /respuestas [POST] - Registrar una respuesta a un topico
- /respuestas [GET] - Listar todas las respuestas
- /respuestas/{id} [PUT] - Editar una respuesta por su id en la url
- /respuestas/{id} [DELETE] - Eliminar un registro por el id en la url
- /respuestas/{id} [GET] - Obtener una respuesta por su id
### Autenticacion
- /login - Realiza la autenticacion en la base de datos

Ademas se agrego la funcionalidad de que no se permiten metodos PUT o DELETE si no se esta autenticado
## Resultados desde Postman
### Metodo GET
![image](https://github.com/AndresFonseca132/ForoAlura/assets/125603660/e15b72a1-6ec3-470b-96c4-4b3007cc7f31)
### Metodo POST
![image](https://github.com/AndresFonseca132/ForoAlura/assets/125603660/7a157d23-f4f7-433f-b53a-9727b4472fb6)
### Metodo PUT
![image](https://github.com/AndresFonseca132/ForoAlura/assets/125603660/5545f035-21a2-4d04-a044-8a5930f2beb3)
### Metodo DELETE
![image](https://github.com/AndresFonseca132/ForoAlura/assets/125603660/2eed5527-6e9b-4b2c-a326-16c34e2c8ba5)
### Login
![image](https://github.com/AndresFonseca132/ForoAlura/assets/125603660/7124b652-af06-4b82-b07f-1aa7e307e17d)

## Documentacion Swagger
![image](https://github.com/AndresFonseca132/ForoAlura/assets/125603660/2e01e8ad-7c2b-46b6-aa7e-95380310cc2f)







