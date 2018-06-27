package me.abdelrhman.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import me.abdelrhman.data.model.ProjectEntity

/**
 * Created by Abdelrhman
 * on 6/27/18.
 */
interface ProjectsCache {

    fun clearProjects(): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

    fun areProjectsCached(): Single<Boolean>

    fun setLastCashTime(lastCash: Long): Completable

    fun isProjectsCacheExpired(): Single<Boolean>


}