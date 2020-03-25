package am.xtech.app16.presentation.ui.home

import am.xtech.app16.app.App.Companion.sApiService
import am.xtech.app16.data.model.Application
import am.xtech.app16.data.model.BaseDataModel
import am.xtech.app16.presentation.ui.home.state.HomeViewState
import am.xtech.app16.utils.ApiSuccessResponse
import am.xtech.app16.utils.DataState
import am.xtech.app16.utils.GenericApiResponse
import am.xtech.app16.utils.NetworkBoundResources
import androidx.lifecycle.LiveData

object HomeRepository {

    fun getCurrentApplication(deviceToken: String): LiveData<DataState<HomeViewState>> {
        return object : NetworkBoundResources<BaseDataModel<Application>, HomeViewState>() {
            override fun handleSuccessResponse(response: ApiSuccessResponse<BaseDataModel<Application>>) {
                result.value = DataState.data(
                    null,

                    data = HomeViewState(application = response.body.data, isCreated = true)
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<BaseDataModel<Application>>> {
                return sApiService.getCurrentApplication(deviceToken)
            }
        }.asLiveData()
    }

    fun finishApplication(deviceToken: String): LiveData<DataState<HomeViewState>> {

        return object : NetworkBoundResources<BaseDataModel<Application>, HomeViewState>() {
            override fun handleSuccessResponse(response: ApiSuccessResponse<BaseDataModel<Application>>) {

                result.value = DataState.data(
                    null,
                    data = HomeViewState( isFinished =  true)
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<BaseDataModel<Application>>> {
                return sApiService.finishApplication(deviceToken)
            }

        }.asLiveData()
    }
}