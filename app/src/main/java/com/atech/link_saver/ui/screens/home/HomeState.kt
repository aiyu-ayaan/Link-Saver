package com.atech.link_saver.ui.screens.home

import androidx.paging.PagingData
import com.atech.core.room.link.LinkModel

internal data class HomeState(
    val items: PagingData<LinkModel> = PagingData.empty(),
)
