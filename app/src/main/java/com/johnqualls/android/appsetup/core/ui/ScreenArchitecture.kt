package com.johnqualls.android.appsetup.core.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow

interface Screen<UiEvent, UiState> {

    val presenter: Presenter<UiEvent, UiState>

    fun sendEvent(uiEvent: UiEvent) = presenter.receiveEvent(uiEvent)

    @Composable
    fun Setup() {
        presenter.uiState.collectAsStateWithLifecycle().value.let { state ->
            Render(state)
        }
    }

    @Composable
    fun Render(state: UiState)

    fun navigate(event: UiEvent) {}
}

interface Presenter<UiEvent, UiState> {
    val uiState: StateFlow<UiState>
    fun receiveEvent(event: UiEvent)
    fun updateState(function: (UiState) -> UiState)
}
