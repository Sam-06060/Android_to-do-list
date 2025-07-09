package com.example.todolist

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var taskEditText: EditText
    private lateinit var addbutton: Button
    private lateinit var taskcontainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        taskEditText = findViewById(R.id.editTextText)
        addbutton = findViewById(R.id.addbutton)
        taskcontainer = findViewById(R.id.container)

        addbutton.setOnClickListener {
            val tasktext = taskEditText.text.toString()
            if (tasktext.isNotBlank()) {
                addTask(tasktext)
                taskEditText.text.clear()
            }
        }
    }

    private fun dpToPx(dp: Int): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    private fun addTask(taskText: String) {
        taskcontainer.visibility = View.VISIBLE

        val taskLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val checkbox = CheckBox(this).apply {
            text = taskText
            textSize = 18f
            setOnCheckedChangeListener { _, isChecked ->
                paintFlags = if (isChecked) {
                    paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }

        taskLayout.addView(checkbox)
        taskcontainer.addView(taskLayout)
    }

}
