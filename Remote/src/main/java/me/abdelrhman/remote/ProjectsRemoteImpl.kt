package me.abdelrhman.remote

import io.reactivex.Observable
import me.abdelrhman.data.model.ProjectEntity
import me.abdelrhman.data.repository.ProjectsRemote
import me.abdelrhman.remote.mapper.ProjectsResponseModelMapper
import me.abdelrhman.remote.service.GithubTrendingService
import javax.inject.Inject

/**
 * Created by Abdelrhman
 * on 6/28/18.
 */
open class ProjectsRemoteImpl @Inject constructor(
        private val service: GithubTrendingService,
        private val mapper: ProjectsResponseModelMapper) : ProjectsRemote {




    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
                .map {
                    it.items.map { mapper.mapFromModel(it) }
                }
    }
}