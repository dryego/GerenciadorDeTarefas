package com.tarefas.tarefas.repositories;

import com.tarefas.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarefaRepositori extends JpaRepository<Tarefa, Long> {

    Optional<Tarefa> findById(Long id);
}
