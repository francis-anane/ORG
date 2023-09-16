package com.abstratsystems.org.models

import com.google.gson.annotations.SerializedName

/**
 * Data class representing an Announcement in the ORG Android app.
 * @property id Unique identifier for the announcement.
 * @property authorId Identifier of the member who created the announcement.
 * @property content Content of the announcement.
 * @property createdAt Timestamp indicating when the announcement was created.
 * @property updatedAt Timestamp indicating the last update to the announcement.
 */
data class Announcement(
    @SerializedName("id") var id: String?,
    @SerializedName("author_id") val authorId: String,
    val content: String,
    @SerializedName("created_at") var createdAt: String?,
    @SerializedName("updated_at") var updatedAt: String?
)

/**
 * Data class representing a Message in the ORG Android app.
 * @property id Unique identifier for the message.
 * @property senderId Identifier of the member who sent the message.
 * @property receiverId Identifier of the member who received the message.
 * @property content Content of the message.
 * @property createdAt Timestamp indicating when the message was sent.
 * @property updatedAt Timestamp indicating the last update to the message.
 */
data class Message(
    @SerializedName("id") val id: String?,
    @SerializedName("sender_id") val senderId: String,
    @SerializedName("receiver_id") val receiverId: String,
    val content: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

/**
 * Data class representing a Discussion in the ORG Android app.
 * @property id Unique identifier for the discussion.
 * @property title Title of the discussion.
 * @property members List of member IDs participating in the discussion.
 * @property authorId Identifier of the member who created the discussion.
 * @property createdAt Timestamp indicating when the discussion was created.
 * @property updatedAt Timestamp indicating the last update to the discussion.
 */
data class Discussion(
    @SerializedName("id") val id: String?,
    val title: String,
    val members: ArrayList<String>,
    @SerializedName("author_id") val authorId: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)
