package com.mustafaunlu.basictodo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mustafaunlu.basictodo.databinding.RecyclerRowBinding

class ToDoAdapter(val toDoList : MutableList<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ToDoViewHolder(binding)
    }
    fun addToDo(toDo : ToDo){
        toDoList.add(toDo)
        notifyItemInserted(toDoList.size-1)
    }
    fun deletetoDo(){
        toDoList.removeAll {
            it.isChecked
        }
        notifyDataSetChanged()
    }
    private fun toggleStrikeThrough(itemTextView :TextView, isChecked : Boolean){
        if(isChecked){
            itemTextView.paintFlags=itemTextView.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            itemTextView.paintFlags=itemTextView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }



    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curToDo=toDoList[position]
        holder.itemView.apply {
            holder.binding.tittleTextView.text=curToDo.title
            holder.binding.tittleCheckBox.isChecked=curToDo.isChecked

            toggleStrikeThrough(holder.binding.tittleTextView,curToDo.isChecked)

            holder.binding.tittleCheckBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(holder.binding.tittleTextView,isChecked)
                curToDo.isChecked=!curToDo.isChecked
            }
        }



    }

    override fun getItemCount(): Int {
        return toDoList.size
    }


}