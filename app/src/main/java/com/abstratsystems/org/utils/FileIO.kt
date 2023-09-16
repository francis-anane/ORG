package com.abstratsystems.org.utils

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import android.os.Environment

object FileIO {


    // Function to read text from a file
    fun readFromFile(filePath: String): String {
        val file = File(filePath)
        val stringBuilder = StringBuilder()

        try {
            val reader = BufferedReader(FileReader(file))
            var line: String?

            // Read line by line and append to the StringBuilder
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append('\n')
            }

            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }

    // Function to write text to a file
    fun writeToFile(filePath: String, content: String) {
        val file = File(filePath)

        try {
            val writer = BufferedWriter(FileWriter(file))

            // Write the content to the file
            writer.write(content)

            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




    fun publicDocumentsPath(): String {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            // Get the documents directory
            val documentsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            documentsDirectory.absolutePath
            // Now, documentsPath contains the path to the documents directory
        } else {
            // External storage is not available or not writable
            println("External storage is not available or not writable.")
            ""
        }
    }

    fun publicPicturesPath(): String {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            // Get the documents directory
            val picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            picturesDirectory.absolutePath
            // Now, documentsPath contains the path to the documents directory
        } else {
            // External storage is not available or not writable
            println("External storage is not available or not writable.")
            ""
        }
    }

    fun appStoragePath(): String {
            // Get the documents directory
            val filesDirectory = Environment.getExternalStorageDirectory()
            return filesDirectory.absolutePath
    }

}