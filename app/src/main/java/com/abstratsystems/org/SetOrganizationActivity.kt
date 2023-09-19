package com.abstratsystems.org

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.abstratsystems.org.utils.Instances
import com.abstratsystems.org.utils.SetColor
import com.abstratsystems.org.utils.UpdateObJect


class SetOrganizationActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var headEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var websiteEditText: EditText
    private lateinit var moreDataCheckBox: CheckBox
    private lateinit var headquarterCountryEditText: EditText
    private lateinit var headquarterStateEditText: EditText
    private lateinit var headquarterCityEditText: EditText
    private lateinit var logoImageButton: ImageButton
    private lateinit var saveOrganizationButton: Button

    private var currentLogoPath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_organization)  // Layout file
        SetColor.actionBar(this, "#2a6099")

        // Initialize views
        initViews()


        // Set an OnCheckedChangeListener for the CheckBox to show/hide additional fields
        moreDataCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // If the CheckBox is checked, reveal additional fields
                headquarterCountryEditText.visibility = View.VISIBLE
                headquarterCityEditText.visibility = View.VISIBLE
                headquarterStateEditText.visibility = View.VISIBLE
                logoImageButton.visibility = View.VISIBLE
            } else {
                // If the CheckBox is unchecked, hide additional fields
                headquarterCountryEditText.visibility = View.GONE
                headquarterCityEditText.visibility = View.GONE
                headquarterStateEditText.visibility = View.GONE
                logoImageButton.visibility = View.GONE
            }
        }
        saveOrganizationButton.setOnClickListener{
            UpdateObJect.organization(Instances.organization.id!!, Instances.organization)
            emptyEditTextFields()
        }
    }


    /**
     * Initialize views
     */

    private fun initViews(){
        // Initialize view objects
        nameEditText= findViewById(R.id.editTextName)
        headEditText = findViewById(R.id.editTextHeadName)
        phoneEditText = findViewById(R.id.editTextPhone)
        emailEditText = findViewById(R.id.editTextEmail)
        websiteEditText = findViewById(R.id.editTextWebsite)

        moreDataCheckBox = findViewById(R.id.checkBoxMoreData)

        headquarterCountryEditText = findViewById(R.id.editTextHeadquarterCountry)
        headquarterStateEditText = findViewById(R.id.editTextHeadquarterState)
        headquarterCityEditText = findViewById(R.id.editTextHeadquarterCity)
        logoImageButton = findViewById(R.id.imageButtonLogo)

        saveOrganizationButton = findViewById(R.id.buttonSaveOrganization)
        SetColor.viewsBackgroundTint(listOf(saveOrganizationButton), "#2a6099")

    }

    /**
     * Creates a JSON object with member data.
     *
     * @return JSON object containing member data.
     */
    private fun setOrganizationData(){
        Instances.organization.name = nameEditText.text.toString()
        Instances.organization.head = headEditText.text.toString()
        Instances.organization.phone = phoneEditText.text.toString()
        Instances.organization.email = emailEditText.text.toString()
        Instances.organization.website = websiteEditText.text.toString()
        Instances.organization.country = headquarterCountryEditText.text.toString()
        Instances.organization.state = headquarterStateEditText.text.toString()
        Instances.organization.city = headquarterCityEditText.text.toString()
        //jsonData.put("logo", currentLogoPath)
    }

    private fun emptyEditTextFields() {
        nameEditText.text.clear()
        headEditText.text.clear()
        phoneEditText.text.clear()
        emailEditText.text.clear()
        websiteEditText.text.clear()
        headquarterCountryEditText.text.clear()
        headquarterStateEditText.text.clear()
        headquarterCityEditText.text.clear()
    }
}



