package com.abstratsystems.org

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.abstratsystems.org.ui.theme.ORGTheme
import android.widget.Button
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Main activity button views
        val checkinBtn : Button = findViewById(R.id.checkinBtn)
        //val organizationBtn : Button = findViewById(R.id.organizationBtn)
        //val addMemberBtn : Button = findViewById(R.id.addMemberBtn)

        // Executor instance to pass to the defined createBiometricPrompt method
        val executor = ContextCompat.getMainExecutor(this)
        // Get biometric prompt instance
        val biometricPrompt = createBiometricPrompt(this, executor)
        val promptInfo = createPromptInfo()

        // Set onclick listener for addMemberBtn
//        addMemberBtn.setOnClickListener {
//            // create add member intent
//            val intent = Intent(this@MainActivity, AddMemberActivity::class.java)
//            intent.action = Intent.ACTION_VIEW
//            startActivity(intent)
//
//        }
        // Set onclick listener for checkinBtn
        checkinBtn.setOnClickListener {
            // Display biometric prompt dialog
            biometricPrompt.authenticate(promptInfo)
        }

    }


// Add biometric authentication
private fun createBiometricPrompt(
    activity: AppCompatActivity,
    executor: Executor
): BiometricPrompt {
    return BiometricPrompt(activity, executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult
            ) {
                // Fingerprint authentication successful
                super.onAuthenticationSucceeded(result)
                Toast.makeText(applicationContext,
                    "Authentication succeeded!", Toast.LENGTH_SHORT)
                    .show()

            }

            override fun onAuthenticationFailed() {
                // Fingerprint authentication failed
                super.onAuthenticationFailed()
                Toast.makeText(applicationContext, "Authentication failed",
                    Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onAuthenticationError(
                errorCode: Int,
                errString: CharSequence
            ) {
                // Handle authentication errors
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(applicationContext,
                    "Authentication error: $errString", Toast.LENGTH_SHORT)
                    .show()
            }
        })
}

// Biometric authentication prompt info
private fun createPromptInfo(): BiometricPrompt.PromptInfo {
    return BiometricPrompt.PromptInfo.Builder()
        .setTitle("Fingerprint Authentication")
        .setSubtitle("Place your finger on the fingerprint sensor")
        .setNegativeButtonText("Cancel")
        .build()
}

}