package am.xtech.app16.data.model

import com.google.gson.annotations.SerializedName

data class CreateApplication(

    @field:SerializedName("device_token")
    val deviceToken: String? = null,

    @field:SerializedName("first_name")
    val firstName: String? = null,

    @field:SerializedName("last_name")
    val lastName: String? = null,


    @field:SerializedName("middle_name")
    val middleName: String? = null,

    @field:SerializedName("out_address")
    val outAddress: String? = null,

    @field:SerializedName("out_datetime")
    val outDatetime: String? = null,

    @field:SerializedName("visiting_address_and_name")
    val visitingAddressAndName: String? = null,

    @field:SerializedName("visiting_reason")
    val visitingReason: String? = null,

    @field:SerializedName("planned_return_datetime")
    val plannedReturnDatetime: String? = null
	)