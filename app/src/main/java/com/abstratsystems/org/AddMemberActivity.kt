package com.abstratsystems.org

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Button

/**
 * Creates an Activity to add a new member to the organization.
 * @property editTextName the name entry field of the member.
 * @property editTextPhone entry field to get member's phone number.
 * @property editTextEmailAddress entry field to get member email address.
 * @property editTextCountry entry field to get member's country.
 * @property editTextState entry field to get member's state.
 * @property editTextCity entry field to get member's city.
 * @property editTextOrganization entry field to get member's organization.
 * @property editTextDepartment entry field to get member's department.
 * @property editTextRole entry field to get member's role.
 */
class AddMemberActivity : AppCompatActivity() {

    // Member data entry fields to get member identity
    private lateinit var editTextName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextCountry: EditText
    private lateinit var editTextState: EditText
    private lateinit var editTextCity: EditText
    private lateinit var editTextOrganization: EditText
    private lateinit var editTextDepartment: EditText
    private lateinit var editTextRole: EditText

    // Variables to hold the text entered into the EditText fields
    private var name: String = ""
    private var phone: String = ""
    private var emailAddress: String = ""
    private var country: String = ""
    private var state: String = ""
    private var city: String = ""
    private var organization: String = ""
    private var department: String = ""
    private var role: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)

        // Initialize the EditText fields
        editTextName = findViewById(R.id.editTextName)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress)
        editTextCountry = findViewById(R.id.editTextCountry)
        editTextState = findViewById(R.id.editTextState)
        editTextCity = findViewById(R.id.editTextCity)
        editTextOrganization = findViewById(R.id.editTextOrganization)
        editTextDepartment = findViewById(R.id.editTextDepartment)
        editTextRole = findViewById(R.id.editTextRole)




        // TODO: Add code here to handle button clicks or other functionality
    }
}
