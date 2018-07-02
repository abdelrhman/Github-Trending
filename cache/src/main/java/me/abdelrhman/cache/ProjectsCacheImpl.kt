package me.abdelrhman.cache

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import me.abdelrhman.cache.db.ProjectsDatabase
import me.abdelrhman.cache.mapper.CachedProjectMapper
import me.abdelrhman.cache.model.Config
import me.abdelrhman.data.model.ProjectEntity
import me.abdelrhman.data.repository.ProjectsCache
import javax.inject.Inject

/**
 * Created by Abdelrhman
 * on 6/30/18.
 */
class ProjectsCacheImpl @Inject constructor(
        private val projectsDatabase: ProjectsDatabase,
        private val mapper: CachedProjectMapper
) : ProjectsCache {
    override fun clearProjects(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().insertProjects(projects.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getProjects().toObservable().map {
            it.map {
                mapper.mapFromCached(it)
            }
        }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects().toObservable().map {
            it.map {
                mapper.mapFromCached(it)
            }
        }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return projectsDatabase.cachedProjectsDao().getProjects().isEmpty.map {
            !it
        }
    }

    override fun setLastCacheTime(lastCash: Long): Completable {
        return Completable.defer {
            projectsDatabase.cachedConfigDao().insertConfig(Config(lastCacheTime = lastCash))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = ( 60 * 10 * 1000).toLong()
        return projectsDatabase.cachedConfigDao().getConfig()
                .single(Config(lastCacheTime = 0))
                .map{
                    currentTime - it.lastCacheTime > expirationTime
                }
    }
}