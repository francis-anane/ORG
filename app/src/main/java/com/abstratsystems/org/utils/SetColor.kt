package com.abstratsystems.org.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

object SetColor {
    fun actionBar(context: Context, colorHex: String){
        // Define ActionBar object
        if (context is AppCompatActivity) {
            val actionBar: ActionBar? = (context as AppCompatActivity).supportActionBar
            val colorDrawable = ColorDrawable(Color.parseColor(colorHex))
            // Set BackgroundDrawable of actionbar
            actionBar!!.setBackgroundDrawable(colorDrawable)
        }

    }

    fun viewsBackgroundTint(views: List<View>, colorHex: String) {
        val color = Color.parseColor(colorHex)
        for(view in views){
            view.backgroundTintList = ColorStateList.valueOf(color)

        }
        
    }
}

