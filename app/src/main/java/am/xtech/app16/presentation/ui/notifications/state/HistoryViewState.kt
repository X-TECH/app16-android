package am.xtech.app16.presentation.ui.notifications.state

import am.xtech.app16.data.model.Application

data class HistoryViewState(
    var applicationList: List<Application>? = null
)