package com.abstratsystems.org.utils

import Member
import android.util.Log
import com.abstratsystems.org.models.Message
import com.abstratsystems.org.models.Organization
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import java.util.UUID

object DataInit {
    var allMembers = ArrayList<Member>()
    var allMessages = ArrayList<Message>()

    fun initOrganization(context: Context){
        val id = FileIO.readId(FileIO.appStoragePath(context) + "/id.txt").trim()
        Log.i("The Read id is:", id)
        if(id == ""){
            Instances.organization.id = UUID.randomUUID().toString()
            // Write the Object id to a file
            FileIO.writeId(FileIO.appStoragePath(context) + "/id.txt", Instances.organization.id!!)
            // Save object to remotes storage
            CreateObJect.organization(Instances.organization)

        } else{
            // Initialize organization data with data from remote if the id file is not empty
            Instances.orgApiService.getOrganization(id)
                .enqueue(object : Callback<Organization> {
                    override fun onResponse(
                        call: Call<Organization>,
                        response: Response<Organization>
                    ) {
                        if (response.isSuccessful) {
                            val organization = response.body()
                            if(organization != null){
                                Instances.organization = organization
                            } else{
                                Instances.organization.id = id
                            }
                        } else {
                            Log.i("Failed Response:", response.toString())
                        }
                    }
                    override fun onFailure(call: Call<Organization>, t: Throwable) {
                        Log.i("Connection Failed:", t.toString())
                    }

                })



        }
    }

    fun initMembers(){
        Instances.orgApiService.getAllMembers()
            .enqueue(object : Callback<ArrayList<Member>> {
                override fun onResponse(
                    call: Call<ArrayList<Member>>,
                    response: Response<ArrayList<Member>>
                ) {
                    if (response.isSuccessful) {

                        allMembers = response.body()!!

                    } else {
                        Log.i("Failed to get members:", response.toString())
                    }
                }

                override fun onFailure(call: Call<ArrayList<Member>>, t: Throwable) {
                    Log.i("Connection Failed", t.toString())
                }

            })
    }
    fun initMessages(){
        Instances.orgApiService.getAllMessages()
            .enqueue(object : Callback<ArrayList<Message>> {
                override fun onResponse(
                    call: Call<ArrayList<Message>>,
                    response: Response<ArrayList<Message>>
                ) {
                    if (response.isSuccessful) {

                        allMessages = response.body()!!

                    } else {
                        println("Failed: $response")
                    }
                }

                override fun onFailure(call: Call<ArrayList<Message>>, t: Throwable) {
                    println("Connection Failed:")
                }

            })
    }
}