package com.guiga.tarefas.ui

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
                description = binding.tilDescripition.text
            )
            TarefaDataSource.insertTarefa(tarefa) // Corrigido para acessar via instância
            Log.e("TAG", "insertListeners" + TarefaDataSource.getList())
            finish()
        }
        binding.btCancelar.setOnClickListener {
            finish()
        }
    }

}

//15:30
