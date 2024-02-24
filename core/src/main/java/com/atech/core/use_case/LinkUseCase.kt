package com.atech.core.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.atech.core.room.link.LinkDoa
import com.atech.core.room.link.LinkModel
import javax.inject.Inject


data class LinkUseCase @Inject constructor(
    val addLink: AddLink,
    val updateLink: UpdateLink,
    val getAllLink: GetAllLink,
)

data class GetAllLink @Inject constructor(
    private val doa: LinkDoa
) {
    operator fun invoke(
        type: LinkDoa.LinkGETType = LinkDoa.LinkGETType.HOME
    ) = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            initialLoadSize = 20,
        ),
    ) {
        doa.getLink(
            type = type
        )
    }.flow
}


data class AddLink @Inject constructor(
    private val dao: LinkDoa
) {
    suspend operator fun invoke(
        linkModel: LinkModel
    ) {
        dao.insert(
            linkModel
        )
    }
}

data class UpdateLink @Inject constructor(
    private val dao: LinkDoa
) {
    suspend operator fun invoke(
        linkModel: LinkModel
    ) {
        dao.update(
            linkModel
        )
    }
}

