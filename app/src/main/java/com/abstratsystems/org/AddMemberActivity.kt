package com.abstratsystems.org
import Member
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.abstratsystems.org.models.Organization
import com.abstratsystems.org.utils.CreateObJect
import com.abstratsystems.org.utils.DataInit
import com.abstratsystems.org.utils.UpdateObJect
import com.google.gson.Gson
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.util.UUID


class AddMemberActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var organizationEditText: EditText
    private lateinit var departmentEditText: EditText
    private lateinit var roleEditText: EditText
    private lateinit var moreDataCheckBox: CheckBox

    private lateinit var countryEditText: EditText
    private lateinit var stateEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var memberImageButton: ImageButton
    private lateinit var saveMemberButton: Button

    private lateinit var currentPhotoPath: String
    private val IMAGE_CAPTURE_REQUEST = 1

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member) // Replace with your layout file

        // Initialize views
        initViews()


        // Set an OnCheckedChangeListener for the CheckBox to show/hide additional fields
        moreDataCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // If the CheckBox is checked, reveal additional fields
                departmentEditText.visibility = View.VISIBLE
                roleEditText.visibility = View.VISIBLE
                countryEditText.visibility = View.VISIBLE
                stateEditText.visibility = View.VISIBLE
                cityEditText.visibility = View.VISIBLE
                memberImageButton.visibility = View.VISIBLE
            } else {
                // If the CheckBox is unchecked, hide additional fields
                departmentEditText.visibility = View.GONE
                roleEditText.visibility = View.GONE
                countryEditText.visibility = View.GONE
                stateEditText.visibility = View.GONE
                cityEditText.visibility = View.GONE
                memberImageButton.visibility = View.GONE

            }
        }

        memberImageButton.setOnClickListener{
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), IMAGE_CAPTURE_REQUEST)
            } else {
                // Permission already granted, proceed with camera capture.
                takePictureIntent()
            }
        }
        // Save data to database with button click
        saveMemberButton.setOnClickListener{
            // Covert the json data to string for serializing into to Member instance
            val jsonString = setMemberData().toString()
            val gson = Gson()
            println("Data String: $jsonString")

            val member: Member = gson.fromJson(jsonString, Member::class.java)
//            // Load the image file
//            val imageFile = File(currentPhotoPath)
//            // Create the image part for member object
//            val requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile)
//            val image: MultipartBody.Part = MultipartBody.Part.createFormData("image", imageFile.name, requestBody)
//
            // save data with api call
            CreateObJect.member(member)
            if(CreateObJect.isSuccessful) {
                // Add member object to current local data
                DataInit.allMembers.add(member)
                Organization.members.add(member.id)
                // Update the organization members list remotely
                UpdateObJect.organization(Organization.id, Organization)
                while (!UpdateObJect.isSuccessful){
                    UpdateObJect.organization(Organization.id, Organization)
                }

            }
            emptyEditTextFields()

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CAPTURE_REQUEST && resultCode == Activity.RESULT_OK) {
            // Handle the image capture result here.
            //Toast.makeText(this, "Image: $currentPhotoPath", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Initialize views
     */

    private fun initViews(){
        // Initialize view objects
        nameEditText = findViewById(R.id.editTextName)
        phoneEditText = findViewById(R.id.editTextPhone)
        emailEditText = findViewById(R.id.editTextEmail)
        organizationEditText = findViewById(R.id.editTextOrganization)
        departmentEditText = findViewById(R.id.editTextDepartment)
        roleEditText = findViewById(R.id.editTextRole)
        moreDataCheckBox = findViewById(R.id.checkBoxMoreData)

        countryEditText = findViewById(R.id.editTextCountry)
        stateEditText = findViewById(R.id.editTextState)
        cityEditText = findViewById(R.id.editTextCity)
        memberImageButton = findViewById(R.id.imageButtonMember)

        saveMemberButton = findViewById(R.id.buttonSaveMember)

    }

    /**
     * Creates a JSON object with member data.
     *
     * @return JSON object containing member data.
     */
    private fun setMemberData(): JSONObject {
        val jsonData = JSONObject()
        jsonData.put("id", UUID.randomUUID().toString())
        jsonData.put("name", nameEditText.text.toString())
        jsonData.put("phone", phoneEditText.text.toString())
        jsonData.put("email", emailEditText.text.toString())
        jsonData.put("country", countryEditText.text.toString())
        jsonData.put("state", stateEditText.text.toString())
        jsonData.put("city", cityEditText.text.toString())
        jsonData.put("organization", organizationEditText.text.toString())
        jsonData.put("department", departmentEditText.text.toString())
        jsonData.put("role", roleEditText.text.toString())
        jsonData.put("created_at", LocalDateTime.now().toString())
        jsonData.put("updated_at", LocalDateTime.now().toString())
        jsonData.put("organization_id", Organization.id)
        //jsonData.put("image", image)
        return jsonData
    }

    private fun emptyEditTextFields() {
        nameEditText.text.clear()
        phoneEditText.text.clear()
        emailEditText.text.clear()
        organizationEditText.text.clear()
        departmentEditText.text.clear()
        roleEditText.text.clear()
        countryEditText.text.clear()
        stateEditText.text.clear()
        cityEditText.text.clear()
    }


    private fun takePictureIntent() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Ensure that there's a camera activity to handle the intent
        if (pictureIntent.resolveActivity(packageManager) != null) {
            // Create a file to save the image
            val photoFile: File = createImageFile()


            // Continue only if the file was successfully created
            if (photoFile != null) {
                // assign path to a variable
                currentPhotoPath = photoFile.absolutePath
                val photoURI: Uri = FileProvider.getUriForFile(
                    this,
                    "com.abstratsystems.org.provider",
                    photoFile
                )
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(pictureIntent, IMAGE_CAPTURE_REQUEST)
            }
        }

    }



    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val name = nameEditText.text.toString()
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "${name}_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

}
