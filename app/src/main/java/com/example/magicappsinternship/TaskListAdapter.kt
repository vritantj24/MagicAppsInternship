package com.example.magicappsinternship

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TaskListAdapter(private val listener : TaskItemClicked) : RecyclerView.Adapter<TaskViewHolder>() {

    private val items: ArrayList<Tasks> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        val viewHolder = TaskViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.taskName
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateTasks(updatedTasks: ArrayList<Tasks>) {
        items.clear()
        items.addAll(updatedTasks)
        notifyDataSetChanged()
    }

}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
}

interface TaskItemClicked {
    fun onItemClicked(item: Tasks)
}