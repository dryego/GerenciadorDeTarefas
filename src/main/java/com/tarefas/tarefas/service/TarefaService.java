package com.tarefas.tarefas.service;

import com.tarefas.tarefas.dto.TarefaEditadaDTO;
import com.tarefas.tarefas.model.Tarefa;
import com.tarefas.tarefas.repositories.TarefaRepositori;
import com.tarefas.tarefas.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    TarefaRepositori tarefaRepositori;

    public Resposta<Tarefa> cadastroTarefa(String titulo, String descricao, String prioridade){
        boolean realizado = false;

        Tarefa novaTarefa = new Tarefa(titulo,descricao,realizado,prioridade);
        tarefaRepositori.save(novaTarefa);

        return new Resposta<>(200,"Tarefa cadastrada com sucesso.", novaTarefa);
    }

    public Resposta<List<Tarefa>> listaTarefas (){
        List<Tarefa> tarefas = tarefaRepositori.findAll();

        if(!tarefas.isEmpty()){
            return new Resposta<>(200,"Tarefas encontradas.", tarefas.stream().toList());
        } else {
          return new Resposta<>(404,"Tarefas nao encontradas.",null);
        }
    }

    public  Resposta<Tarefa> buscarTarfea(Long id){
        Optional<Tarefa> tarefa = tarefaRepositori.findById(id);

        if(tarefa.isPresent()){
            return new Resposta<>(200,"Tarefa encontrada.", tarefa.get());
        }
        return new Resposta<>(404,"Tarefa não encontrada.", null);
    }

    public Resposta<Tarefa> editaTarefa(Long id, TarefaEditadaDTO tarefaEditadaDTO){
        Optional<Tarefa> tarefaExistente = tarefaRepositori.findById(id);

        if(tarefaExistente.isPresent()){
            Tarefa tarefaEditada = tarefaExistente.get();

            if(tarefaEditadaDTO.getTitulo() != null){
                tarefaEditada.setTitulo(tarefaEditadaDTO.getTitulo());
            }
            if(tarefaEditadaDTO.getDescricao() != null){
                tarefaEditada.setDescricao(tarefaEditadaDTO.getDescricao());
            }
            if(tarefaEditadaDTO.getPrioridade() != null){
                tarefaEditada.setPrioridade(tarefaEditadaDTO.getPrioridade());
            }

            tarefaRepositori.save(tarefaEditada);

            return new Resposta<>(200,"Tarefa editada com sucesso.", tarefaEditada);
        }

        return new Resposta<>(404,"Tarefa não encontrada.", null);
    }

    public Resposta<Tarefa> tarefaRealizada(Long id,Boolean realizada){
        Optional<Tarefa> tarefa = tarefaRepositori.findById(id);

        if(tarefa.isPresent()){
            Tarefa tarefaExiste = tarefa.get();

            tarefaExiste.setRealizada(realizada);

            tarefaRepositori.save(tarefaExiste);

            return new Resposta<>(200,"Tarefa realizada.", tarefaExiste);
        }
        return  new Resposta<>(404,"Tarefa nao localizada.", null);
    }

    public Resposta<Tarefa> excluirTarefa(Long id){
        Optional<Tarefa> tarefa = tarefaRepositori.findById(id);

        if(tarefa.isPresent()){
            tarefaRepositori.delete(tarefa.get());

            return new Resposta<>(200,"A tarefa foi excluida com sucesso.", tarefa.get());
        }
        return new Resposta<>(404,"Tarefa não localizada.", null);
    }
}
