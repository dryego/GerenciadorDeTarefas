package com.tarefas.tarefas.controller;

import com.tarefas.tarefas.dto.TarefaEditadaDTO;
import com.tarefas.tarefas.model.Tarefa;
import com.tarefas.tarefas.service.TarefaService;
import com.tarefas.tarefas.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @PostMapping("/cadastro")
    public ResponseEntity<Object> postTarefaCadastro(@RequestBody Tarefa tarefa) {
        try{
            Resposta<Tarefa> novaTarefa =tarefaService.cadastroTarefa(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade());

            if(novaTarefa.getData() == null){
                return ResponseEntity.status(404).body("Tarefa não cadastrada.");
            }

            return ResponseEntity.status(novaTarefa.getStatus()).body(novaTarefa.getMenssagen());
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno.");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> getListarTarefas () {
        try {
            Resposta<List<Tarefa>> tarefas = tarefaService.listaTarefas();

            if (tarefas.getData() == null){
                return ResponseEntity.status(tarefas.getStatus()).body(tarefas.getMenssagen());
            } else {
                return ResponseEntity.status(tarefas.getStatus()).body(tarefas.getData());
            }

        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno. " +e );
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Object> getBuscarTarefa(@PathVariable Long id){
        Resposta<Tarefa> tarefa = tarefaService.buscarTarfea(id);

        if(tarefa.getData() == null){
            return ResponseEntity.status(tarefa.getStatus()).body(tarefa.getMenssagen());
        }

        return ResponseEntity.status(tarefa.getStatus()).body(tarefa.getData());
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> putEditarTarefa(@PathVariable Long id, @RequestBody TarefaEditadaDTO tarefaEditadaDTO){
     try{
         Resposta<Tarefa> tarefaEditada = tarefaService.editaTarefa(id,tarefaEditadaDTO);

         if(tarefaEditada.getData() == null){
             return ResponseEntity.status(tarefaEditada.getStatus()).body(tarefaEditada.getMenssagen());
         }

         return ResponseEntity.status(tarefaEditada.getStatus()).body(tarefaEditada.getMenssagen());
     } catch (Exception e){
         return ResponseEntity.status(500).body("Erro interno. " + e);
     }
    }

    @PutMapping("/realizada/{id}")
    public ResponseEntity<Object> putTarefaRealizada(@PathVariable Long id, @RequestBody Boolean realizada){
        Resposta<Tarefa> tarefaRealizada = tarefaService.tarefaRealizada(id, realizada);

        if(tarefaRealizada.getData() == null){
            return ResponseEntity.status(tarefaRealizada.getStatus()).body(tarefaRealizada.getMenssagen());
        }

        return  ResponseEntity.status(tarefaRealizada.getStatus()).body(tarefaRealizada.getMenssagen());
    }
}
