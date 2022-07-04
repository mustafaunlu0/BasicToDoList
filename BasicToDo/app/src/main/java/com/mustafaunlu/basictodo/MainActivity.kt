package com.mustafaunlu.basictodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafaunlu.basictodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var adapter : ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= ToDoAdapter(mutableListOf())

        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(this)

        binding.addButton.setOnClickListener {

            val toDoTittle=binding.titleEditText.text.toString()

            if(!toDoTittle.isEmpty()) {
                val toDo=ToDo(toDoTittle)
                adapter.addToDo(toDo)
                binding.titleEditText.text.clear()

            }


        }

        binding.deleteButton.setOnClickListener {
            adapter.deletetoDo()
        }





    }
}