package me.abdelrhman.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import me.abdelrhman.data.model.ProjectEntity
import me.abdelrhman.data.repository.ProjectsCache
import me.abdelrhman.data.repository.ProjectsDataStore
import javax.inject.Inject

/**
 * Created by Abdelrhman
 * on 6/27/18.
 */
open class ProjectsCacheDataStore @Inject constructor(private val projectsCash: ProjectsCache) : ProjectsDataStore {
    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCash.saveProjects(projects).andThen(projectsCash.setLastCashTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return projectsCash.clearProjects()
    }


    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCash.getProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCash.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCash.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return projectsCash.setProjectAsNotBookmarked(projectId)
    }
}