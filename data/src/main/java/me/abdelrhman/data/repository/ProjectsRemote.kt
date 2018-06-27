package me.abdelrhman.data.repository

import io.reactivex.Observable
import me.abdelrhman.data.model.ProjectEntity

/**
 * Created by Abdelrhman
 * on 6/27/18.
 */

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>

}