package com.atech.core.room.link

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LinkDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(link: LinkModel)

    @Update
    suspend fun update(link: LinkModel)

    @Delete
    suspend fun delete(link: LinkModel)


    @Query("DELETE FROM link_table where isDeleted = 1")
    suspend fun emptyBin()

    @Query("DELETE FROM link_table")
    suspend fun deleteAll()


    fun getLink(type: LinkGETType) =
        when (type) {
            LinkGETType.HOME -> getAllLink()
            LinkGETType.ARCHIVE -> getAllArchiveLink()
            LinkGETType.BIN -> getAllBinLink()
        }

    @Query("SELECT * FROM link_table WHERE isArchive = 1 ORDER BY createdAt DESC")
    fun getAllArchiveLink(): PagingSource<Int, LinkModel>

    @Query("SELECT * FROM link_table WHERE isDeleted = 1 ORDER BY createdAt DESC")
    fun getAllBinLink(): PagingSource<Int, LinkModel>

    @Query("SELECT * FROM link_table ORDER BY createdAt DESC")
    fun getAllLink(): PagingSource<Int, LinkModel>
    enum class LinkGETType {
        HOME, ARCHIVE, BIN
    }

}