package com.example.todo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = TaskListFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_item_add_task -> {
                Toast.makeText(applicationContext,"Add new task", Toast.LENGTH_SHORT).show()
                moveToAddTask()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun moveToAddTask(){
        val fragment = TaskAddFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(null)
            .commit()
    }
}