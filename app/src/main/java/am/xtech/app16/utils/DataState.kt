package am.xtech.app16.utils

data class DataState<T>(
    var message: Event<String>? = null,
    var code: Int? = null,

    var loading: Boolean = false,
    var data: Event<T>? = null
) {
    companion object {
        fun <T> error(message: String, code: Int): DataState<T> {
            return DataState(
                code = code,
                message = Event(
                    message
                ), loading = false, data = null
            )
        }

        fun <T> loading(isLoading: Boolean): DataState<T> {
            return DataState(
                code = null,
                message = null,
                loading = true,
                data = null
            )
        }

        fun <T> data(message: String? = null, data: T? = null): DataState<T> {
            return DataState(
                code = null,
                message = Event.messageEvent(
                    message
                ),
                loading = false,
                data = Event.dataEvent(data)
            )
        }
    }

    override fun toString(): String {
        return "DataState(message=$message, loading=$loading, data=$data)"
    }


}