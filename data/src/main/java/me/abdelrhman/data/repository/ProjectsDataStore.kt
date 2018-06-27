package me.abdelrhman.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import me.abdelrhman.data.model.ProjectEntity

/**
 * Created by Abdelrhman
 * on 6/27/18.
 */
interface ProjectsDataStore {


    fun getProjects(): Observable<List<ProjectEntity>>

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(): Completable

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

}