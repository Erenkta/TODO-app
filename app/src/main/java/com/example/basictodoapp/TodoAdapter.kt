package com.example.basictodoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.basictodoapp.databinding.ItemTodoBinding



class TodoAdapter(  //Control i ile fonksiyonları çıkar
    private val todos:MutableList<Todo>
):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    //Spesifik bir layoutun display edilmesine yarayab bir class (viewHolder classlar) parametre olarak tutması gereken view alır
    // RecylcerView.ViewHolder yaptık çünkü bunu parenti olarak aldı yani burdaki özellikleri kullanabilecek
    class TodoViewHolder(val binding: ItemTodoBinding): ViewHolder(binding.root){
        fun bindItem(todo:Todo,adapter: TodoAdapter){
            binding.tvDesc.text = todo.title
            binding.cbDone.isChecked = todo.isChecked
            adapter.ustunuCiz(binding.tvDesc,todo.isChecked)
            binding.cbDone.setOnCheckedChangeListener { _, isChecked -> //_ Boş bıraktık yani kullanmıcaz demek
                adapter.ustunuCiz(binding.tvDesc,isChecked)
                todo.isChecked = !todo.isChecked
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) { //Dataları işlediğimiz func
        val currItem = todos[position] //o an oluşturulan iteme işaret ediyor
        holder.bindItem(currItem, TodoAdapter(todos))
    }

    private fun ustunuCiz(todoTitle : TextView, isChecked:Boolean){
        if(isChecked){ //Yani checklenmiş ise üstünü çiz
            todoTitle.paintFlags = todoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG //Bu satırı araştır
        }
        else{ //Check kalkmışsa üstünü çizmeyi tersine çevir
            todoTitle.paintFlags = todoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    fun addTodo(todo:Todo){
        todos.add(todo) //eklenicek ama yetmez bunu kullanmakla beraber görünür olmicak
        notifyItemInserted(todos.size -1) // bu demek ki listenin sonuna bir şey eklendi mi bunu fark et
    }
    fun deleteDoneTodos(){ //Lambda fonksiyonlarını araştırs
        todos.removeAll { todo-> //bu todos'a getiricek ve her bir itemi için buna bakıcak gibi düşün
            todo.isChecked //Her item için eğer checkli mi diye bakıcak öyleyse silicek
        }
        notifyDataSetChanged(  ) //Bu da fark edip update atıcak bunları unutma önemli listelerin update olması için
    }


}

