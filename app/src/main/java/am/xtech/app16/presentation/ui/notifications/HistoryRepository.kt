package am.xtech.app16.presentation.ui.notifications

import am.xtech.app16.app.App.Companion.sApiService
import am.xtech.app16.data.model.Application
import am.xtech.app16.data.model.BaseDataModel
import am.xtech.app16.presentation.ui.notifications.state.HistoryViewState
import am.xtech.app16.utils.ApiSuccessResponse
import am.xtech.app16.utils.DataState
import am.xtech.app16.utils.GenericApiResponse
import am.xtech.app16.utils.NetworkBoundResources
import androidx.lifecycle.LiveData

object HistoryRepository {

    fun getHistory(deviceToken: String): LiveData<DataState<HistoryViewState>> {

        return object :
            NetworkBoundResources<BaseDataModel<List<Application>>, HistoryViewState>() {
            override fun handleSuccessResponse(response: ApiSuccessResponse<BaseDataModel<List<Application>>>) {

                result.value = DataState.data(
                    null,
                    data = HistoryViewState(applicationList = response.body.data)
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<BaseDataModel<List<Application>>>> {
                return sApiService.getHistory(deviceToken)
            }

        }.asLiveData()
    }
}