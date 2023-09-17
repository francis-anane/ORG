package com.abstratsystems.org.utils

import Member
import android.util.Log
import com.abstratsystems.org.models.Message
import com.abstratsystems.org.models.Organization
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.util.UUID
import android.content.Context


object DataInit {
    var allMembers = ArrayList<Member>()
    var allMessages = ArrayList<Message>()
    // Place holder for all model objects
    lateinit var modelObject: Any

    fun initOrganization(context: Context){
        val id = FileIO.readId(FileIO.appStoragePath(context) + "/id.txt")
        Log.i("The id is:", id)
        if(id == ""){
            // Initialize a new Organization instance id
            Organization.id = UUID.randomUUID().toString()
            Log.i("New id is:", Organization.id)

            Organization.name = "Org"
            Organization.head = "Org"
            Organization.email = "Org"
            Organization.phone = "Org"
            Organization.website = "Org"
            Organization.country = "Org"
            Organization.state = "Org"
            Organization.city = "org"
            Organization.createdAt = LocalDateTime.now().toString()
            Organization.updatedAt = LocalDateTime.now().toString()
            Organization.members = ArrayList()
            CreateObJect.organization(Organization)
            if(CreateObJect.isSuccessful){
                // Write the Object id to a file
                FileIO.writeId(FileIO.appStoragePath(context) + "/id.txt", Organization.id)
            }
        } else{
            // Initialize organization data with data from remote if the id exists
            MyInstances.orgApiService.getOrganization(id)
                .enqueue(object : Callback<Organization> {
                    override fun onResponse(
                        call: Call<Organization>,
                        response: Response<Organization>
                    ) {
                        if (response.isSuccessful) {

                            val org = response.body()
                            if(org != null){
                                Organization.id = org.id
                                Organization.name = org.name
                                Organization.head = org.head
                                Organization.phone = org.phone
                                Organization.email = org.email
                                Organization.country = org.country
                                Organization.state = org.state
                                Organization.city = org.city
                                Organization.website = org.website
                                Organization.members = org.members
                                Organization.createdAt = org.createdAt
                                Organization.updatedAt = org.updatedAt
                                Organization.logo = org.logo
                            } else{
                                Organization.id = id
                                Organization.name = "Org"
                                Organization.head = "Org"
                                Organization.email = "Org"
                                Organization.phone = "Org"
                                Organization.website = "Org"
                                Organization.country = "Org"
                                Organization.state = "Org"
                                Organization.city = "org"
                                Organization.createdAt = LocalDateTime.now().toString()
                                Organization.updatedAt = LocalDateTime.now().toString()
                                Organization.members = ArrayList()
                                CreateObJect.organization(Organization)
                            }


                        } else {
                            println("Failed Response: $response")
                        }
                    }

                    override fun onFailure(call: Call<Organization>, t: Throwable) {
                        println("Connection Failed:")
                    }

                })



        }
    }

    fun initMembers(){
        MyInstances.orgApiService.getAllMembers()
            .enqueue(object : Callback<ArrayList<Member>> {
                override fun onResponse(
                    call: Call<ArrayList<Member>>,
                    response: Response<ArrayList<Member>>
                ) {
                    if (response.isSuccessful) {

                        allMembers = response.body()!!

                    } else {
                        println("Failed: $response")
                    }
                }

                override fun onFailure(call: Call<ArrayList<Member>>, t: Throwable) {
                    println("Connection Failed:")
                }

            })
    }
    fun initMessages(){
        MyInstances.orgApiService.getAllMessages()
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