import com.abstratsystems.org.models.Announcement
import com.abstratsystems.org.models.Attendance
import com.abstratsystems.org.models.Discussion
import com.abstratsystems.org.models.Message
import com.abstratsystems.org.models.Organization
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Retrofit interface for the ORG Android app API.
 */
interface OrgApiService {
    // Member routes

    @GET("/members")
    fun getAllMembers(): Call<ArrayList<Member>>

    @GET("/members/{member_id}")
    fun getMember(@Path("member_id") memberId: String): Call<Member>

    @POST("/members")
    fun createMember(@Body member: Member): Call<Member>

    @PUT("/members/{member_id}")
    fun updateMember(@Path("member_id") memberId: String, @Body member: Member): Call<Member>

    @DELETE("/members/{member_id}")
    fun deleteMember(@Path("member_id") memberId: String): Call<Void>

    // Event routes

    @GET("/events")
    fun getAllEvents(): Call<ArrayList<Event>>

    @GET("/events/{event_id}")
    fun getEvent(@Path("event_id") eventId: String): Call<Event>

    @POST("/events")
    fun createEvent(@Body event: Event): Call<Event>

    @PUT("/events/{event_id}")
    fun updateEvent(@Path("event_id") eventId: String, @Body event: Event): Call<Event>

    @DELETE("/events/{event_id}")
    fun deleteEvent(@Path("event_id") eventId: String): Call<Void>

    // Organization routes

    @GET("/organizations")
    fun getAllOrganizations(): Call<ArrayList<Organization>>

    @GET("/organizations/{organization_id}")
    fun getOrganization(@Path("organization_id") organizationId: String): Call<Organization>

    @POST("/organizations")
    fun createOrganization(@Body organization: Organization): Call<Organization>

    @PUT("/organizations/{organization_id}")
    fun updateOrganization(
        @Path("organization_id") organizationId: String,
        @Body organization: Organization
    ): Call<Organization>

    @DELETE("/organizations/{organization_id}")
    fun deleteOrganization(@Path("organization_id") organizationId: String): Call<Void>

    // Announcement routes

    @GET("/announcements")
    fun getAllAnnouncements(): Call<ArrayList<Announcement>>

    @GET("/announcements/{announcement_id}")
    fun getAnnouncement(@Path("announcement_id") announcementId: String): Call<Announcement>

    @POST("/announcements")
    fun createAnnouncement(@Body announcement: Announcement): Call<Announcement>

    @PUT("/org/announcements/{announcement_id}")
    fun updateAnnouncement(
        @Path("announcement_id") announcementId: String,
        @Body announcement: Announcement
    ): Call<Announcement>

    @DELETE("/org/announcements/{announcement_id}")
    fun deleteAnnouncement(@Path("announcement_id") announcementId: String): Call<Void>

    // Message routes

    @GET("/org/messages")
    fun getAllMessages(): Call<ArrayList<Message>>

    @GET("/org/messages/{message_id}")
    fun getMessage(@Path("message_id") messageId: String): Call<Message>

    @POST("/org/messages")
    fun createMessage(@Body message: Message): Call<Message>

    @PUT("/org/messages/{message_id}")
    fun updateMessage(@Path("message_id") messageId: String, @Body message: Message): Call<Message>

    @DELETE("/org/messages/{message_id}")
    fun deleteMessage(@Path("message_id") messageId: String): Call<Void>

    // Discussion routes

    @GET("/org/discussions")
    fun getAllDiscussions(): Call<ArrayList<Discussion>>

    @GET("/org/discussions/{discussion_id}")
    fun getDiscussion(@Path("discussion_id") discussionId: String): Call<Discussion>

    @POST("/org/discussions")
    fun createDiscussion(@Body discussion: Discussion): Call<Discussion>

    @PUT("/org/discussions/{discussion_id}")
    fun updateDiscussion(
        @Path("discussion_id") discussionId: String,
        @Body discussion: Discussion
    ): Call<Discussion>

    @DELETE("/org/discussions/{discussion_id}")
    fun deleteDiscussion(@Path("discussion_id") discussionId: String): Call<Void>

    // Attendance routes

    @GET("/org/attendances")
    fun getAllAttendance(): Call<ArrayList<Attendance>>

    @GET("/org/attendance/{attendance_id}")
    fun getAttendance(@Path("attendance_id") attendanceId: String): Call<Attendance>

    @POST("/org/attendances")
    fun createAttendance(@Body attendance: Attendance): Call<Attendance>

    @PUT("/org/attendance/{attendance_id}")
    fun updateAttendance(
        @Path("attendance_id") attendanceId: String,
        @Body attendance: Attendance
    ): Call<Attendance>

    @DELETE("/org/attendance/{attendance_id}")
    fun deleteAttendance(@Path("attendance_id") attendanceId: String): Call<Void>

   
}