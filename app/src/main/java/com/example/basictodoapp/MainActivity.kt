package com.example.basictodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basictodoapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private lateinit var todoAdapter:TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        todoAdapter = TodoAdapter(mutableListOf()) //Boş bir liste yolladık

        binding.rvList.adapter = todoAdapter
        binding.rvList.layoutManager = LinearLayoutManager(this) //layoutManager Ne araştır

        binding.btAdd.setOnClickListener {
            val todoTitle:String = binding.tvTitle.text.toString() //Aşağıya yazdığımız stringi buna atadık
            if(todoTitle.isNotEmpty()){
                val todoitem = Todo(todoTitle) //isChecked'i yollamadık zaten default olarak false idi
                todoAdapter.addTodo(todoitem) //Yukarıda listeye eklenecek uygun _TODO türünde item oluşturduk ve burda da adapter ile ekledik
                binding.tvTitle.text.clear()
            }
        }
        binding.btDelete.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}