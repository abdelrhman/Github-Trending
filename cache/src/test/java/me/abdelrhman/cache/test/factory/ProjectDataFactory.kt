package me.abdelrhman.cache.test.factory

import me.abdelrhman.cache.model.CachedProject
import me.abdelrhman.data.model.ProjectEntity

/**
 * Created by Abdelrhman
 * on 6/30/18.
 */
object ProjectDataFactory {

    fun makeCachedProject(): CachedProject {
        return CachedProject(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                false)
    }

    fun makeBookmarkedCachedProject(): CachedProject {
        return CachedProject(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                true)
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomBoolean())
    }

    fun makeBookmarkedProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                true)
    }
}
