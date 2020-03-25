package am.xtech.app16.presentation.ui.history

import am.xtech.app16.presentation.base.BaseViewModel
import am.xtech.app16.presentation.ui.history.state.HistoryStateEvent
import am.xtech.app16.presentation.ui.history.state.HistoryViewState
import am.xtech.app16.utils.DataState
import androidx.lifecycle.LiveData

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