package com.abstratsystems.org.models

import com.google.gson.annotations.SerializedName

/**
 * Data class representing Attendance in the ORG Android app.
 *
 * @property id Unique identifier for the attendance record.
 * @property attendanceType Type of attendance (regular or special event).
 * @property memberId Identifier of the member associated with the attendance record.
 * @property checkInTime Timestamp indicating when the member checked in.
 * @property checkOutTime Timestamp indicating when the member checked out.
 * @property createdAt Timestamp indicating when the attendance record was created.
 * @property updatedAt Timestamp indicating the last update to the attendance record.
 */
data class Attendance(
    @SerializedName("id") var id: String?,
    @SerializedName("attendance_type") val attendanceType: String,
    @SerializedName("member_id") val memberId: String,
    @SerializedName("check_in_time") val checkInTime: String,
    @SerializedName("check_out_time") val checkOutTime: String,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)

