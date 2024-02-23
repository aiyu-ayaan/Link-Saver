package com.atech.link_saver.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _addLinkState = mutableStateOf(AddLinkState())
    internal val addLinkState: State<AddLinkState> get() = _addLinkState


    private val _homeState = mutableStateOf(HomeState())
    internal val homeState: State<HomeState> get() = _homeState


    fun onAddLinkEvent(event: AddLinkEvents) {
        when (event) {
            is AddLinkEvents.OnShortDesChange ->
                _addLinkState.value = _addLinkState.value.copy(shortDes = event.shortDes)

            is AddLinkEvents.OnLinkChange ->
                _addLinkState.value = _addLinkState.value.copy(
                    link = event.link
                )

            AddLinkEvents.OnClearRequest -> _addLinkState.value = AddLinkState()

            AddLinkEvents.OnCancelClick -> {}
            AddLinkEvents.OnSaveClick -> {}
        }
    }

}