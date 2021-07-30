package com.example.magicappsinternship

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_my_task.*


class MyTaskFragment : Fragment(),TaskItemClicked {

    fun checkIfFragmentAttached(operation: Context.() -> Unit) {
        if (isAdded && context != null) {
            operation(requireContext())
        }
    }

    private lateinit var mAdapter: TaskListAdapter

    private lateinit var tasklist : Array<String>

    private lateinit var taskimages : Array<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_my_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkIfFragmentAttached{ tasklist = requireContext().resources.getStringArray(R.array.task_names)
        taskimages = requireContext().resources.getStringArray(R.array.task_images)
        }

        task_rv.layoutManager = GridLayoutManager(requireContext(),2)
        mAdapter = TaskListAdapter(this)
        fetchData(mAdapter)
        task_rv.adapter=mAdapter
    }

    override fun onItemClicked(item: Tasks) {
        AlertDialog.Builder(requireContext())
            .setTitle("Task")
            .setMessage(item.taskName)
            .setPositiveButton("OK", { dialog, which -> dialog.dismiss()  })
            .create()
            .show()
    }

     private fun fetchData(adapter: TaskListAdapter) {

        val tasksArray = ArrayList<Tasks>()
        for(i in 0..9) {
            val tasks = Tasks(tasklist[i],taskimages[i])
            tasksArray.add(tasks)
        }
        adapter.updateTasks(tasksArray)
    }

}