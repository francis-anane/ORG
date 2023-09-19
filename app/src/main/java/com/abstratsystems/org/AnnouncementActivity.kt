package com.abstratsystems.org

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abstratsystems.org.utils.SetColor

class AnnouncementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcement)
        SetColor.actionBar(this, "#2a6099")
    }
}