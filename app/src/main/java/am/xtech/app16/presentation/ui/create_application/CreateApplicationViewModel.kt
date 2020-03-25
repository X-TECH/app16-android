package am.xtech.app16.presentation.ui.create_application

import am.xtech.app16.presentation.base.BaseViewModel
import am.xtech.app16.presentation.ui.create_application.state.CreateFormStateEvent
import am.xtech.app16.presentation.ui.create_application.state.CreateFormViewState
import am.xtech.app16.utils.DataState
import androidx.lifecycle.LiveData

class CreateApplicationViewModel : BaseViewModel<CreateFormStateEvent,CreateFormViewState>() {
    override fun handleStateEvent(stateEvent: CreateFormStateEvent): LiveData<DataState<CreateFormViewState>> {
        return when(stateEvent){
            is CreateFormStateEvent.registerApplication ->{
                return  CreateApplicationRepository.registerApplication(stateEvent.application)
            }
        }
    }

    override fun initNewViewState(): CreateFormViewState {
        return CreateFormViewState()
    }
}
