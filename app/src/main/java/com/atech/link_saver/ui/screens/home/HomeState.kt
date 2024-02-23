package com.atech.link_saver.ui.screens.home

import com.atech.core.room.link.LinkModel

internal data class HomeState(
    val items : List<LinkModel> = emptyList(),
)
