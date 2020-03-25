package am.xtech.app16.presentation.ui.home

import am.xtech.app16.presentation.base.BaseViewModel
import am.xtech.app16.presentation.ui.home.state.HomeStateEvent
import am.xtech.app16.presentation.ui.home.state.HomeViewState
import am.xtech.app16.utils.DataState
import androidx.lifecycle.LiveData

class HomeViewModel : BaseViewModel<HomeStateEvent, HomeViewState>() {
    override fun handleStateEvent(stateEvent: HomeStateEvent): LiveData<DataState<HomeViewState>> {

        return when (stateEvent) {
            is HomeStateEvent.getCurrentApplication -> {
                return HomeRepository.getCurrentApplication(stateEvent.deviceToken)
            }

            is HomeStateEvent.finishApplication -> {
                return HomeRepository.finishApplication(stateEvent.deviceToken)
            }
        }

    }

    override fun initNewViewState(): HomeViewState {
        return HomeViewState()
    }
}
