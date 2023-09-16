package com.abstratsystems.org

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AddEventActivity : AppCompatActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var datePicker: DatePicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        // Initialize UI components
        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById<EditText>(R.id.descriptionEditText)
        datePicker = findViewById<DatePicker>(R.id.datePicker)

        // Set up the Toolbar (AppBar)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the "Up" button for navigation
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.add_event_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.saveEventMenuItem -> {
                // Handle the "Save" action here
                saveEvent()
                true
            }

            R.id.home -> {
                // Handle the "Up" button
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveEvent() {
        // Retrieve data from EditText and DatePicker
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1 // Month is 0-based, so add 1
        val year = datePicker.year

        // Handle saving the event data to your database or storage
        // You can implement this part based on your app's requirements
    }
}