package com.guiga.tarefas.extensions

import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.SimpleTimeZone

private val locale = Locale("pt", "BR")

fun Date.format() : String{
    return  SimpleDateFormat("dd/MM/yyyy").format(this)
}

var TextInputLayout.text : String
    get() = editText?.text?.toString() ?: ""
    set(value){
        editText?.setText(value)
    }