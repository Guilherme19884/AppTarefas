package com.guiga.tarefas.dataSource

import com.guiga.tarefas.model.Tarefa

object TarefaDataSource {
    private val list = arrayListOf<Tarefa>()

    fun getList() = list.toList()

    fun insertTarefa(tarefa: Tarefa) {
        if(tarefa.id == 0) {
            val novoId = list.size + 1 // Calcula o novo id
            val novaTarefa = Tarefa(
                novoId,
                tarefa.title,
                tarefa.hour,
                tarefa.date,
                tarefa.description
            )
            list.add(novaTarefa)
        }else{
            list.remove(tarefa)
            list.add(tarefa)
        }
    }

    fun findById(taskId: Int) = list.find { it.id == taskId }

    fun deleteTarefa(tarefa: Tarefa) {
        list.remove(tarefa)
    }

}