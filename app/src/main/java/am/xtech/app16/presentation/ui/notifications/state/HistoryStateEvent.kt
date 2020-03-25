package am.xtech.app16.presentation.ui.notifications.state

import am.xtech.app16.presentation.ui.home.state.HomeStateEvent

sealed class HistoryStateEvent {
    class getHistory(val deviceToken : String) : HistoryStateEvent()

}