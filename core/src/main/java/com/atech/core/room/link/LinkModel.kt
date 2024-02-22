package com.atech.core.room.link

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "link_table")
data class LinkModel(
    @PrimaryKey(autoGenerate = false)
    val link: String,
    val sortDes: String,

    val fetchTitle: String? = null,
    val fetchDes: String? = null,
    val fetchImage: String? = null,
    val fetchIcon: String? = null,

    val isArchive: Boolean = false,
    val isDeleted: Boolean = false,
    val isFetchDone: Boolean = false,

    val deletedAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)
