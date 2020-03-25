package am.xtech.app16.data.model.error

import com.google.gson.annotations.SerializedName

data class FieldsItem(

	@field:SerializedName("messages")
	val messages: List<String>? = null,

	@field:SerializedName("key")
	val key: String? = null
)