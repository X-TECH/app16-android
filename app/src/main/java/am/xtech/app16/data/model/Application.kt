package am.xtech.app16.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Application(

	@field:SerializedName("out_address")
	val outAddress: String? = null,

	@field:SerializedName("qr_token")
	val qrToken: String? = null,

	@field:SerializedName("visiting_reason")
	val visitingReason: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,


	@field:SerializedName("finished_at")
	val finishedAt: String? = null,

	@field:SerializedName("middle_name")
	val middleName: String? = null,

	@field:SerializedName("out_longitude")
	val outLongitude: String? = null,

	@field:SerializedName("out_datetime")
	val outDatetime: String? = null,

	@field:SerializedName("visiting_address_and_name")
	val visitingAddressAndName: String? = null,

	@field:SerializedName("visiting_longitude")
	val visitingLongitude: String? = null,

	@field:SerializedName("visiting_latitude")
	val visitingLatitude: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("device_token")
	val deviceToken: String? = null,

	@field:SerializedName("planned_return_datetime")
	val plannedReturnDatetime: String? = null,

	@field:SerializedName("out_latitude")
	val outLatitude: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null
):Serializable