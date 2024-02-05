package com.guiga.tarefas.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import com.guiga.tarefas.dataSource.TarefaDataSource
import com.guiga.tarefas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val adapter by lazy {TaskListAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvListTasks.adapter = adapter
        updateList()

        insertListeners()
    }

    fun insertListeners(){
        binding.faButton.setOnClickListener {
            startActivityForResult(Intent(this, AddTaskActivity::class.java), CREAT_NEW_TASK)
        }
        adapter.listenerEdit = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(intent, CREAT_NEW_TASK)
            updateList()
        }
        adapter.listenerDelete = {
            TarefaDataSource.deleteTarefa(it)
            updateList()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREAT_NEW_TASK && resultCode == Activity.RESULT_OK) {
            updateList()
        }
    }

    private fun updateList(){
        adapter.submitList(TarefaDataSource.getList())
    }

    companion object
     val CREAT_NEW_TASK = 1000
}