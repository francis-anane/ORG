package com.abstratsystems.org.models

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.time.LocalDateTime
import java.util.UUID

/**
 * Data class representing an Organization in the ORG Android app.
 *
 * @property id Unique identifier for the organization.
 * @property name of the organization.
 * @property head Name of the head of the organization
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
data class Organization (
    @SerializedName("id") var id: String? = null,
    var name: String = "ORG",
    var head: String = "ORG",
    var phone: String = "",
    var email: String = "",
    var website: String = "",
    var country: String = "",
    var state: String = "",
    var city: String = "",
    var logo: MultipartBody.Part? = null, // This field will hold the image data,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)