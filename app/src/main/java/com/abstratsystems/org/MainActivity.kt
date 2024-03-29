package com.abstratsystems.org

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abstratsystems.org.utils.DataInit
import com.abstratsystems.org.utils.Instances
import com.abstratsystems.org.utils.SetColor
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

/**
 * Main menu Activity for the organization.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var fabAddMember: FloatingActionButton
    private lateinit var setOrganizationImageView: ImageView
    private lateinit var imageButtonMembers: ImageView
    private lateinit var imageButtonMessage: ImageView
    private lateinit var imageButtonAnnouncement: ImageView
    private lateinit var imageButtonEvent: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SetColor.actionBar(this, "#2a6099")


        // Code inside this call is executed in a background thread

        Instances.coroutineScope.launch{
            // Initialize Organization
            DataInit.initOrganization(applicationContext)
            //Initialize members data
            DataInit.initMembers()
            // Initialize Messages
            DataInit.initMessages()

        }

        initViews()

        // Event to add a new organization on button click
        setOrganizationImageView.setOnClickListener{
            val intent = Intent(this@MainActivity, SetOrganizationActivity::class.java)
            startActivity(intent)
        }

        // Event to add a new member on button click
        fabAddMember.setOnClickListener {
            // Create an intent to add a new user
            val intent = Intent(this@MainActivity, AddMemberActivity::class.java)
            startActivity(intent)
        }

        imageButtonMembers.setOnClickListener {
            // Create an intent to see members
            val intent = Intent(this@MainActivity, MembersActivity::class.java)
            startActivity(intent)
        }

        imageButtonMessage.setOnClickListener {
            // Create an intent to send and read messages
            val intent = Intent(this@MainActivity, MessageActivity::class.java)
            startActivity(intent)
        }

        imageButtonAnnouncement.setOnClickListener {
            val intent = Intent(this@MainActivity, AnnouncementActivity::class.java)
            startActivity(intent)
        }

        imageButtonEvent.setOnClickListener {
            val intent = Intent(this@MainActivity, EventActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                // Handle settings menu item click
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // Initialize all views
    private fun initViews() {
        bottomAppBar = findViewById(R.id.bottomAppBar)
        fabAddMember = findViewById(R.id.FabAddMember)
        setOrganizationImageView = findViewById(R.id.buttonAddOrganization)
        imageButtonMembers = findViewById(R.id.imageButtonMembers)
        imageButtonMessage = findViewById(R.id.imageButtonMessage)
        imageButtonAnnouncement = findViewById(R.id.imageButtonAnnouncement)
        imageButtonEvent = findViewById(R.id.imageButtonEvent)

        SetColor.actionBar(this, "#2a6099")
        SetColor.viewsBackgroundTint(listOf(fabAddMember), "#2a6099")

    }
}
