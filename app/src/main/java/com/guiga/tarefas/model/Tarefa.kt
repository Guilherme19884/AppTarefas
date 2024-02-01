package com.guiga.tarefas.model

class Tarefa(
    val id: Int = 0,
    val title: String,
    val hour: String,
    val date: String,
    val description: String
){
    // Construtor de cópia
    constructor(tarefa: Tarefa) : this(
        tarefa.id,
        tarefa.title,
        tarefa.hour,
        tarefa.date,
        tarefa.description
    )
    // Sobrescrever o método toString() para uma representação legível
    override fun toString(): String {
        return "Tarefa(id=$id, title='$title', hour='$hour', date='$date', description='$description')"
    }
}