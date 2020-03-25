package am.xtech.app16.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResources<ResponseObject, ViewStateType> {

    protected val result = MediatorLiveData<DataState<ViewStateType>>()

    init {
        result.value = DataState.loading(true)

        GlobalScope.launch(IO) {
            withContext(Main) {

                val apiResponse = createCall()

                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)
                    handleNetworkCall(response)
                }
            }
        }
    }

    fun handleNetworkCall(response: GenericApiResponse<ResponseObject>) {
        when (response) {

            is ApiSuccessResponse -> {
                handleSuccessResponse(response)
            }

            is ApiEmptyResponse -> {
                onReturnError("HTTP 204. Empty response",204)
            }

            is ApiErrorResponse -> {
                onReturnError(response.errorMessage,response.code)
            }
        }
    }

    fun onReturnError(message: String,code:Int) {
        result.value = DataState.error(message,code)
    }

    fun asLiveData() = result as LiveData<DataState<ViewStateType>>

    abstract fun handleSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

}