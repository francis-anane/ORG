package com.abstratsystems.org

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import com.abstratsystems.org.utils.SetColor

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        SetColor.actionBar(this, "#2a6099")
    }
}