package com.guiga.tarefas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.guiga.tarefas.R
import com.guiga.tarefas.databinding.ItemTaskBinding
import com.guiga.tarefas.model.Tarefa

class TaskListAdapter : ListAdapter <Tarefa, TaskListAdapter.TarefaViewHolder >(DiffCallback()){

    var listenerEdit : (Tarefa) -> Unit = {}
    var listenerDelete : (Tarefa) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TarefaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TarefaViewHolder(
    private val binding: ItemTaskBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Tarefa) {
            binding.tvTitleTask.text = item.title
            binding.tvDateTimeTask.text = "${item.date}  ${item.hour}"
            binding.icImageMore.setOnClickListener{
                showPopUp(item)
            }
        }

        private fun showPopUp(item: Tarefa) {
            val icMore = binding.icImageMore
            val popUpMenu = PopupMenu(icMore.context, icMore)
            popUpMenu.menuInflater.inflate(R.menu.popup_menu, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when(it.itemId){
                R.id.action_edit -> listenerEdit(item)
                R.id.action_delete -> listenerDelete(item)
            }
            return@setOnMenuItemClickListener true
        }
            popUpMenu.show()
    }
}

class DiffCallback : DiffUtil.ItemCallback<Tarefa>(){
    override fun areItemsTheSame(oldItem: Tarefa, newItem: Tarefa) = oldItem ==newItem

    override fun areContentsTheSame(oldItem: Tarefa, newItem: Tarefa) = oldItem.id == newItem.id
    }
}