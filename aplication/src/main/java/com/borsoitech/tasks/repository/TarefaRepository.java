package com.borsoitech.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.borsoitech.tasks.entity.Tarefa;
import com.borsoitech.tasks.entity.Usuario;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    List<Tarefa> findByUsuario(Usuario usuario);
}
