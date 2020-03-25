package am.xtech.app16.presentation.ui.create_application

import am.xtech.app16.app.App.Companion.sApiService
import am.xtech.app16.data.model.Application
import am.xtech.app16.data.model.BaseDataModel
import am.xtech.app16.data.model.CreateApplication
import am.xtech.app16.presentation.ui.create_application.state.CreateFormViewState
import am.xtech.app16.utils.ApiSuccessResponse
import am.xtech.app16.utils.DataState
import am.xtech.app16.utils.GenericApiResponse
import am.xtech.app16.utils.NetworkBoundResources
import androidx.lifecycle.LiveData

object CreateApplicationRepository {

    fun registerApplication(application: CreateApplication): LiveData<DataState<CreateFormViewState>> {

        return object : NetworkBoundResources<BaseDataModel<Application>, CreateFormViewState>() {
            override fun handleSuccessResponse(response: ApiSuccessResponse<BaseDataModel<Application>>) {

                result.value = DataState.data(
                    null,
                    data = CreateFormViewState( application = response.body.data)
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<BaseDataModel<Application>>> {
                return sApiService.registerApplication(application)
            }

        }.asLiveData()
    }
}