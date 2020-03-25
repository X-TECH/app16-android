package am.xtech.app16.data.model.error

import com.google.gson.annotations.SerializedName

data class Error(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("fields")
	val fields: List<FieldsItem>? = null
)