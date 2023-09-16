import com.google.gson.annotations.SerializedName

/**
 * Data class representing an Event in the ORG Android app.
 *
 * @property id Unique identifier for the event.
 * @property eventTitle Title of the event.
 * @property description Description of the event.
 * @property attendees List of member IDs attending the event.
 * @property rsvps List of member IDs who RSVPed for the event.
 * @property location Location of the event.
 * @property eventDate Date and time of the event.
 * @property authorId Identifier of the member who created the event.
 * @property createdAt Timestamp indicating when the event was created.
 * @property updatedAt Timestamp indicating the last update to the event information.
 */
data class Event(
    @SerializedName("id") var id: String?,
    @SerializedName("event_title") val eventTitle: String,
    val description: String,
    val attendees: ArrayList<String>,
    val rsvps: ArrayList<String>,
    val location: String,
    @SerializedName("event_date") val eventDate: String,
    @SerializedName("author_id") val authorId: String,
    @SerializedName("created_at") var createdAt: String?,
    @SerializedName("updated_at") var updatedAt: String?
)
