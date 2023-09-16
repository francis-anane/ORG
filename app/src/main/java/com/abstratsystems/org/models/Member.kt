import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
/**
 * Data class representing a Member in the ORG Android app.
 *
 * @property id Unique identifier for the member.
 * @property name Member's name.
 * @property phone Member's phone number.
 * @property email Member's email address.
 * @property country Member's country.
 * @property state Member's state.
 * @property city Member's city.
 * @property image Member's image (URL or base64 representation).
 * @property organizationId Identifier of the organization to which the member belongs.
 * @property department Member's department within the organization.
 * @property role Member's role or position within the organization.
 * @property createdAt Timestamp indicating when the member was created.
 * @property updatedAt Timestamp indicating the last update to the member's information.
 */
data class Member(
    val name: String,
    val phone: String?,
    val email: String?,
    val country: String?,
    val state: String?,
    val city: String?,
    @SerializedName("image") var image: MultipartBody.Part? = null, // This field will hold the image data
    @SerializedName("organization_id") val organizationId: String?,
    val department: String?,
    val role: String?,
    val id: String,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String
)
