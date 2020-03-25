package am.xtech.app16.presentation.ui.history.state

sealed class HistoryStateEvent {
    class getHistory(val deviceToken : String) : HistoryStateEvent()

}