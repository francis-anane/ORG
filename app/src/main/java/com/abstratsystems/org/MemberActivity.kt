package com.abstratsystems.org

import Member
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abstratsystems.org.utils.DataInit
import com.abstratsystems.org.utils.MyInstances
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class MemberActivity: AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var organizationTextView: TextView
    private lateinit var departmentTextView: TextView
    private lateinit var roleTextView: TextView
    private lateinit var countryTextView: TextView
    private lateinit var stateTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var memberImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)
        // initialize the views
        initViews()
        setProfileData()



    }

    // Initialize views

    @SuppressLint("SetTextI18n")
    private fun initViews(){
        nameTextView = findViewById<TextView>(R.id.textViewName)
        phoneTextView = findViewById<TextView>(R.id.textViewPhone)
        emailTextView = findViewById<TextView>(R.id.textViewEmail)
        organizationTextView = findViewById<TextView>(R.id.textViewOrganization)
        departmentTextView = findViewById<TextView>(R.id.textViewDepartment)
        roleTextView = findViewById<TextView>(R.id.textViewRole)
        countryTextView = findViewById<TextView>(R.id.textViewCountry)
        stateTextView = findViewById<TextView>(R.id.textViewState)
        cityTextView = findViewById<TextView>(R.id.textViewCity)
        memberImageView = findViewById<ImageView>(R.id.imageViewProfilePicture)



    }

    @SuppressLint("SetTextI18n")
    fun setProfileData(){
        if (DataInit.modelObject is Member){
            val member = DataInit.modelObject as Member
            nameTextView.text = "Name: ${member.name}"
            phoneTextView.text = "Phone: ${member.phone}"
            emailTextView.text = "Email: ${member.email}"
            departmentTextView.text = "Department: ${member.department}"
            roleTextView.text = "Role: ${member.role}"
            countryTextView.text = "Country: ${member.country}"
            stateTextView.text = "State: ${member.state}"
            cityTextView.text = "City: ${member.city}"

            //TODO: Set picture data, Set Organization data

        }

    }
}