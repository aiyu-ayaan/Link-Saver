package com.atech.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atech.core.room.link.LinkDoa
import com.atech.core.room.link.LinkModel

@Database(
    entities = [
        LinkModel::class,
    ],
    version = 1
)
abstract class LinkDatabase : RoomDatabase() {
    abstract val dao: LinkDoa

    companion object {
        const val DATABASE_NAME = "link_database"
    }
}