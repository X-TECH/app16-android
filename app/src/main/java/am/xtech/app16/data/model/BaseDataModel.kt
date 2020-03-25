package am.xtech.app16.data.model

import com.google.gson.annotations.SerializedName

data class BaseDataModel<T>(

	@field:SerializedName("data")
	val data: T? = null
)