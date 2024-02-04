package com.guiga.tarefas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.guiga.tarefas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val adapter by lazy {TaskListAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvListTasks.adapter = adapter

        insertListeners()
    }

    fun insertListeners(){
        binding.faButton.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }
}