package com.atech.link_saver.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.atech.core.room.link.LinkModel
import com.atech.core.use_case.LinkUseCase
import com.atech.core.utils.isLink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val linkUseCase: LinkUseCase
) : ViewModel() {
    private val _addLinkState = mutableStateOf(AddLinkState())
    internal val addLinkState: State<AddLinkState> get() = _addLinkState

    private val _links = MutableStateFlow<PagingData<LinkModel>>(PagingData.empty())
    internal val links: StateFlow<PagingData<LinkModel>> get() = _links.asStateFlow()

//    private val _homeState = mutableStateOf(HomeState())
//    internal val homeState: State<HomeState> get() = _homeState

//    val links = linkUseCase.getAllLink().cachedIn(viewModelScope)

    private var job: Job? = null

    init {
        getLinks()
    }

    private fun getLinks() {
        job?.cancel()
        job = viewModelScope.launch {
            linkUseCase.getAllLink()
                .cachedIn(viewModelScope)
                .onEach {
                    _links.value = it
                }
                .launchIn(viewModelScope)
        }
    }


    fun onAddLinkEvent(event: AddLinkEvents) {
        when (event) {
            is AddLinkEvents.OnShortDesChange ->
                _addLinkState.value = _addLinkState.value.copy(shortDes = event.shortDes)

            is AddLinkEvents.OnLinkChange ->
                _addLinkState.value = _addLinkState.value.copy(
                    link = event.link,
                    isLinkError = !event.link.isLink()
                )

            AddLinkEvents.OnClearRequest -> _addLinkState.value = AddLinkState()

            AddLinkEvents.OnCancelClick -> {}
            AddLinkEvents.OnSaveClick -> {
                val shortDes = _addLinkState.value.shortDes
                val link = _addLinkState.value.link
                if (link.isNotEmpty() || link.isLink())
                    viewModelScope.launch {
                        linkUseCase.addLink(
                            linkModel = LinkModel(
                                link = link,
                                sortDes = shortDes
                            )
                        )
                        getLinks()
                    }
            }
        }
    }

}