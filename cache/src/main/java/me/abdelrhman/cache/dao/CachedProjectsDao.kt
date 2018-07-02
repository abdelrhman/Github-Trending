package me.abdelrhman.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import me.abdelrhman.cache.db.ProjectConstants.DELETE_PROJECTS
import me.abdelrhman.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import me.abdelrhman.cache.db.ProjectConstants.QUERY_PROJECTS
import me.abdelrhman.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import me.abdelrhman.cache.model.CachedProject

/**
 * Created by Abdelrhman
 * on 6/30/18.
 */
@Dao
abstract class CachedProjectsDao {

    @Query(QUERY_PROJECTS)
    @JvmSuppressWildcards
    abstract fun getProjects(): Flowable<List<CachedProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertProjects(projects: List<CachedProject>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<CachedProject>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookmarked: Boolean,
                                   projectId: String)


}