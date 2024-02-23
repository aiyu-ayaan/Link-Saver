package com.atech.link_saver.ui.screens.home

import com.atech.core.room.link.LinkModel

data class HomeScreenState(
    val items : List<LinkModel> = emptyList(),
)
