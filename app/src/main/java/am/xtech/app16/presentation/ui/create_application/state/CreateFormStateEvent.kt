package am.xtech.app16.presentation.ui.create_application.state

import am.xtech.app16.data.model.CreateApplication


sealed class CreateFormStateEvent {
    class registerApplication(val application: CreateApplication) : CreateFormStateEvent()
}