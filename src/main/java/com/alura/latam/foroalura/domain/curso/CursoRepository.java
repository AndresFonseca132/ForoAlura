package com.alura.latam.foroalura.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c WHERE c.lenguaje = :lenguaje")
    List<Curso> getAllByLenguaje(Lenguaje lenguaje);
}
