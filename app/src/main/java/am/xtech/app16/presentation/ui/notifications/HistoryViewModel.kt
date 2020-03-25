package am.xtech.app16.presentation.ui.notifications

import am.xtech.app16.presentation.base.BaseViewModel
import am.xtech.app16.presentation.ui.create_application.CreateApplicationRepository
import am.xtech.app16.presentation.ui.create_application.state.CreateFormStateEvent
import am.xtech.app16.presentation.ui.notifications.state.HistoryStateEvent
import am.xtech.app16.presentation.ui.notifications.state.HistoryViewState
import am.xtech.app16.utils.DataState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryViewModel : BaseViewModel<HistoryStateEvent,HistoryViewState>() {

    override fun handleStateEvent(stateEvent: HistoryStateEvent): LiveData<DataState<HistoryViewState>> {

        return when(stateEvent){
            is HistoryStateEvent.getHistory ->{
                return  HistoryRepository.getHistory(stateEvent.deviceToken)
            }
        }
    }

    override fun initNewViewState(): HistoryViewState {
        return  HistoryViewState()
    }
}