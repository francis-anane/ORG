package com.abstratsystems.org.models

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

/**
 * Data class representing an Organization in the ORG Android app.
 *
 * @property id Unique identifier for the organization.
 * @property name of the organization.
 * @property head Name of the head of the organization
 * @property members List of member IDs associated with the organization.
 * @property phone Organization's phone number.
 * @property email Organization's email address.
 * @property website Organization's website URL.
 * @property country Country of organization Headquarters
 * @property state State of organization Headquarters
 * @property city City of organization Headquarters
 * @property logo Customization data for the organization.
 * @property createdAt Timestamp indicating when the organization was created.
 * @property updatedAt Timestamp indicating the last update to the organization's information.
 */
object Organization {
    @SerializedName("id") lateinit var id: String
    lateinit var name: String
    lateinit var head: String
    lateinit var members: ArrayList<String>
    lateinit var phone: String
    lateinit var email: String
    lateinit var website: String
    lateinit var country: String
    lateinit var state: String
    lateinit var city: String
    var logo: MultipartBody.Part? = null // This field will hold the image data,
    @SerializedName("created_at") lateinit var createdAt: String
    @SerializedName("updated_at") lateinit var updatedAt: String
}
