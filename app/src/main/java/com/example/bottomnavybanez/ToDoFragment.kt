package com.example.bottomnavybanez

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class ToDoFragment : Fragment() {

    private lateinit var todoList: ArrayList<TaskItem>
    private lateinit var adapter: TaskAdapter
    private lateinit var newItemEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do, container, false)

        val todoListView = view.findViewById<ListView>(R.id.listView)
        newItemEditText = view.findViewById(R.id.editTextItem)
        val addButton = view.findViewById<Button>(R.id.buttonAdd)

        todoList = ArrayList()
        adapter = TaskAdapter(requireContext(), todoList)
        todoListView.adapter = adapter

        addButton.setOnClickListener {
            val newItem = newItemEditText.text.toString()
            if (newItem.isNotEmpty()) {
                val task = TaskItem(newItem)
                todoList.add(task)
                adapter.notifyDataSetChanged()
                newItemEditText.setText("")
            }
        }

        return view
    }

    data class TaskItem(var taskText: String, var isChecked: Boolean = false)

    inner class TaskAdapter(context: Context, private val tasks: ArrayList<TaskItem>) :
        ArrayAdapter<TaskItem>(context, 0, tasks) {

        private var lastClickTime: Long = 0
        private val doubleClickThreshold: Long = 300

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val listItemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

            val currentTask = getItem(position) ?: return listItemView

            val checkBox = listItemView.findViewById<CheckBox>(R.id.checkBox)
            checkBox.isChecked = currentTask.isChecked

            val taskTextView = listItemView.findViewById<TextView>(R.id.textViewTask)
            taskTextView.text = currentTask.taskText

            val taskImageView = listItemView.findViewById<ImageView>(R.id.imageViewTaskIcon)
            taskImageView.setImageResource(R.drawable.ic_launcher_background)

            listItemView.setOnClickListener {
                val clickTime = System.currentTimeMillis()
                if (clickTime - lastClickTime < doubleClickThreshold) {
                    // Double click detected
                    showEditDeleteDialog(position)
                }
                lastClickTime = clickTime
            }

            return listItemView
        }

        private fun showEditDeleteDialog(position: Int) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Edit or Delete Task")
                .setMessage("What do you want to do?")
                .setPositiveButton("Edit") { dialog, _ ->
                    showEditTaskDialog(position)
                    dialog.dismiss()
                }
                .setNegativeButton("Delete") { dialog, _ ->
                    tasks.removeAt(position)
                    notifyDataSetChanged()
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        private fun showEditTaskDialog(position: Int) {
            val editTaskDialog = AlertDialog.Builder(context)
            editTaskDialog.setTitle("Edit Task")

            val input = EditText(context)
            input.setText(tasks[position].taskText)
            editTaskDialog.setView(input)

            editTaskDialog.setPositiveButton("Save") { dialog, _ ->
                tasks[position].taskText = input.text.toString()
                notifyDataSetChanged()
                dialog.dismiss()
            }

            editTaskDialog.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }

            editTaskDialog.show()
        }
    }
}
