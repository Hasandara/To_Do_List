package com.example.to_do_list

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list.Adapter.ToDoAdapter
import com.example.to_do_list.Model.ToDoModel
import com.example.to_do_list.Utils.DatabaseHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), DialogCloseListener {

    private lateinit var tasksRecyclerView: RecyclerView
    private lateinit var taskAdapter: ToDoAdapter
    private lateinit var fab: FloatingActionButton

    private var taskList: MutableList<ToDoModel> = mutableListOf()

    private lateinit var db: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        db = DatabaseHandler(this)
        db.openDatabase()

        tasksRecyclerView = findViewById(R.id.tasksRecyclerview)
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = ToDoAdapter(db, this)
        tasksRecyclerView.adapter = taskAdapter

        fab = findViewById(R.id.fab)

        val itemTouchHelper = ItemTouchHelper(RecyclerItemTouchHelper(taskAdapter, this))
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView)

        taskList = db.getAllTasks().toMutableList()
        taskList.reverse()
        taskAdapter.setTasks(taskList)

        fab.setOnClickListener {
            AddNewTask.newInstance().show(supportFragmentManager, AddNewTask.TAG)
        }
    }

    override fun handleDialogClose(dialog: DialogInterface) {
        taskList = db.getAllTasks().toMutableList()
        taskList.reverse()
        taskAdapter.setTasks(taskList)
        taskAdapter.notifyDataSetChanged()
    }
}
