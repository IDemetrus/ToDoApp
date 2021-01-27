package com.example.todo

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "TaskListFragment"

class TaskListFragment : Fragment() {

    private val taskListViewModel: TaskListViewModel by lazy {
        ViewModelProvider(this).get(TaskListViewModel::class.java)
    }
    private lateinit var taskRecyclerView: RecyclerView
    private var adapter:TaskAdapter? = TaskAdapter(emptyList())
    private lateinit var taskAddFragment: TaskAddFragment

    companion object {
        fun newInstance() : TaskListFragment{
            return TaskListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list,container,false)

        taskRecyclerView = view.findViewById(R.id.task_recycler_view) as RecyclerView
        taskRecyclerView.layoutManager = LinearLayoutManager(context)
        taskRecyclerView.adapter = adapter

        taskAddFragment = TaskAddFragment()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskListViewModel.taskListLiveData.observe(viewLifecycleOwner, { tasks ->
            tasks?.let {
                updateUI(tasks)
            }
        })
    }

    private fun updateUI(tasks: List<Task>) {
        val adapter = TaskAdapter(tasks)
        taskRecyclerView.adapter = adapter
    }


    private inner class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        private lateinit var task: Task
        private val tvTitle: TextView? = itemView.findViewById(R.id.list_item_title)
        private val tvDate: TextView? = itemView.findViewById(R.id.list_item_date)
        private val ivSolved: ImageView? = itemView.findViewById(R.id.list_item_solved)


        init {
            itemView.setOnClickListener(this)
        }

        fun bind(task: Task) {
            this.task = task
            tvTitle?.text = this.task.title
            tvDate?.text = getDate(this.task.date)
            ivSolved?.visibility = if (task.isSolved) View.VISIBLE else View.GONE
        }

        override fun onClick(v: View) {
            Snackbar.make(v,task.title,Snackbar.LENGTH_SHORT).show()
        }
        private fun getDate(date: Date): String {
            val sdf = SimpleDateFormat("EEE, MMM d, yyyy", Locale.ENGLISH)
            return sdf.format(date)
        }
    }

    private inner class TaskAdapter(val tasks: List<Task>) : RecyclerView.Adapter<TaskHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {

            val view = layoutInflater.inflate(R.layout.task_list_item,parent,false)
            val viewAdv = layoutInflater.inflate(R.layout.task_list_item_adv,parent,false)
            return if (viewType == 0) TaskHolder(view) else TaskHolder(viewAdv)
        }

        override fun onBindViewHolder(holder: TaskHolder, position: Int) {
            val task = tasks[position]
            holder.bind(task)
        }

        override fun getItemCount() = tasks.size

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.task_add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.task_add_menu_new -> {Toast.makeText(context, "New task add", Toast.LENGTH_SHORT).show()
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container,taskAddFragment, TAG)
                    ?.addToBackStack(TAG)
                    ?.commit()
                true}
            else -> return super.onOptionsItemSelected(item)
        }

    }
}