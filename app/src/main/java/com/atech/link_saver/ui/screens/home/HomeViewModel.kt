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

}