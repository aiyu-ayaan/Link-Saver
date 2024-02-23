package com.atech.link_saver.ui.screens.bin

import com.atech.core.room.link.LinkModel

internal data class BinState(
    val items: List<LinkModel> = emptyList()
)