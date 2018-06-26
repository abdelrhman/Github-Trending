package me.abdelrhman.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import me.abdelrhman.domain.model.Project

/**
 * Created by Abdelrhman
 * on 6/26/18.
 */
interface ProjectsRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>

}