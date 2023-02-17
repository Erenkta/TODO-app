package com.example.basictodoapp

data class Todo(
//data class'ların öncelikli amacı veri tutmak bundan dolayı constructor gibi davranıyor
    val title:String,
    var isChecked:Boolean = false,

    )
