package com.guiga.tarefas.dataSource

import com.guiga.tarefas.model.Tarefa

object TarefaDataSource {
    private val list = arrayListOf<Tarefa>()

    fun getList() = list

    fun insertTarefa(tarefa: Tarefa) {
        val novoId = list.size + 1 // Calcula o novo id
        val novaTarefa = Tarefa(novoId, tarefa.title, tarefa.hour, tarefa.date, tarefa.description) // Cria uma nova instância de Tarefa com o novo id
        list.add(novaTarefa) // Adiciona a nova tarefa à lista
    }

}