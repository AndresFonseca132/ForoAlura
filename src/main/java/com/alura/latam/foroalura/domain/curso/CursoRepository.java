package com.alura.latam.foroalura.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Interface que extiende de JpaRepository para poder usar los métodos de JPA
// Esta nos permite hacer consultas a la base de datos y ejecutarlas ademas se crea un nuevo metodo
// para poder buscar por lenguaje
public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c WHERE c.lenguaje = :lenguaje")
    List<Curso> getAllByLenguaje(Lenguaje lenguaje);
}
