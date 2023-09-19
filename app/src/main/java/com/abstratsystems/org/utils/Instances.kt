package com.abstratsystems.org.utils

import OrgApiService
import android.content.Context
import com.abstratsystems.org.models.Organization
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Instances {

    // coroutineScope object to execute code in background
    val coroutineScope = CoroutineScope(Dispatchers.IO) // Use Dispatchers.IO for background tasks
    fun python(context: Context): Python{
        // start python
        if(!Python.isStarted()){
            Python.start(AndroidPlatform(context))
        }
        // Instance of python object
        return Python.getInstance()
    }


    // The Retrofit Network library instance
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://abstrat22.tech/org/")
        // Add a Gson converter factory to parse JSON responses
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // An Implementation of the OrgApiService interface is generated by Retrofit
    // for the making network requests
    val orgApiService: OrgApiService = retrofit.create(OrgApiService::class.java)
    // place holder for Organization instances
    var organization = Organization()

    // Place holder for all model objects
    lateinit var modelObject: Any
}