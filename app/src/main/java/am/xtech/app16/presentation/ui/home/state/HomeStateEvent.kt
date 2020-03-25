package am.xtech.app16.presentation.ui.home.state

sealed class HomeStateEvent {
    class getCurrentApplication(val deviceToken : String) : HomeStateEvent()
    class finishApplication(val deviceToken : String) : HomeStateEvent()
}