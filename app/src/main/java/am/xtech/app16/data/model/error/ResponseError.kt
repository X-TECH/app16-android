package am.xtech.app16.data.model.error

import com.google.gson.annotations.SerializedName

data class ResponseError(

	@field:SerializedName("error")
	val error: Error? = null
)