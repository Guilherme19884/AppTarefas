package com.guiga.tarefas.ui

import android.app.Activity
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.guiga.tarefas.dataSource.TarefaDataSource
import com.guiga.tarefas.databinding.ActivityAddTaskBinding
import com.guiga.tarefas.extensions.format
import com.guiga.tarefas.extensions.text
import com.guiga.tarefas.model.Tarefa
import java.util.Date
import java.util.TimeZone

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddTaskBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(TASK_ID)){
            val taskId = intent.getIntExtra(TASK_ID, 0)
            TarefaDataSource.findById(taskId)?.let {
                binding.tilEntradaTextTitle.text = it.title
                binding.tilHora.text = it.hour
                binding.tilData.text = it.date
                binding.tilDescripition.text = it.description
            }
        }
        insertListeners()
    }

    private fun insertListeners() {
        binding.tilData.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilData.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager,"DATE_PICKER_TAG")
        }
        binding.tilHora.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener{
                val minute =  if(timePicker.minute in 0..9)"0${timePicker.minute}" else timePicker.minute
                val hour =if(timePicker.hour in 0..9)"0${timePicker.hour}" else timePicker.hour
                binding.tilHora.text = "$hour : $minute"
            }
            timePicker.show(supportFragmentManager, null)
        }
        binding.btSalvar.setOnClickListener {
            //botão de adicionar
            val tarefa = Tarefa(
                title = binding.tilEntradaTextTitle.text,
                date = binding.tilData.text,
                hour = binding.tilHora.text,
                description = binding.tilDescripition.text,
                id = intent.getIntExtra(TASK_ID, 0)
            )
            TarefaDataSource.insertTarefa(tarefa) // Corrigido para acessar via instância
            Log.e("TAG", "insertListeners" + TarefaDataSource.getList())
            updateListInMainActivity()
            finish()
        }
        binding.btCancelar.setOnClickListener {
            finish()
        }
    }
    private fun updateListInMainActivity() {
        val resultIntent = Intent().apply {
            setResult(Activity.RESULT_OK, this)
        }
        setResult(Activity.RESULT_OK, resultIntent)
    }
    companion object{
        const val TASK_ID = "task_id"
    }
}


