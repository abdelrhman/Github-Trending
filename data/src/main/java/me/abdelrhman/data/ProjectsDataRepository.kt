package me.abdelrhman.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import me.abdelrhman.data.mapper.ProjectMapper
import me.abdelrhman.data.repository.ProjectsCache
import me.abdelrhman.data.store.ProjectsDataStoreFactory
import me.abdelrhman.domain.model.Project
import me.abdelrhman.domain.repository.ProjectsRepository
import javax.inject.Inject

/**
 * Created by Abdelrhman
 * on 6/27/18.
 */

class ProjectsDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val cache: ProjectsCache,
        private val factory: ProjectsDataStoreFactory)
    : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(),
                cache.isProjectsCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {it ->
                    factory.getDataStore(it.first, it.second).getProjects()
                }
                .flatMap { projects ->
                    factory.getCacheDataStore()
                            .saveProjects(projects)
                            .andThen(Observable.just(projects))
                }
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
                .map {
                    it.map { mapper.mapFromEntity(it) }
                }
    }


}