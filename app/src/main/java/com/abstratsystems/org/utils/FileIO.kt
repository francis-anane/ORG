package com.abstratsystems.org.utils

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import android.os.Environment
import android.content.Context
import android.util.Log

object FileIO {
    // Function to read text from a file
    fun readId(idPath: String): String {

        var id: String = ""
        Log.i("The id source is:", idPath)
        try {
            val reader = BufferedReader(FileReader(File(idPath)))
            // Read the id file
            id = reader.readLine()
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.i("The id read is: ", id)
        return id
    }

    // Function to write text to a file
    fun writeId(idPath: String, id: String) {
        try {
            val writer = BufferedWriter(FileWriter(File(idPath)))

            // Write the id to the file
            Log.i("Before writing id:", id)
            Log.i("The writing des:", idPath)
            writer.write(id)

            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    fun appStoragePath(context: Context): String {
            // Get the documents directory
            val filesDirectory = context.filesDir
            return filesDirectory.absolutePath
    }

}