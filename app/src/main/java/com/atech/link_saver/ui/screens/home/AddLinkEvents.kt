package com.atech.link_saver.ui.screens.home

sealed interface AddLinkEvents {
    data class OnLinkChange(val link: String) : AddLinkEvents
    data class OnShortDesChange(val shortDes: String) : AddLinkEvents
    data object OnSaveClick : AddLinkEvents
    data object OnCancelClick : AddLinkEvents

    data object OnClearRequest : AddLinkEvents
}