package me.abdelrhman.remote.test.factory

import me.abdelrhman.data.model.ProjectEntity
import me.abdelrhman.remote.model.OwnerModel
import me.abdelrhman.remote.model.ProjectModel
import me.abdelrhman.remote.model.ProjectsResponseModel

/**
 * Created by Abdelrhman
 * on 6/28/18.
 */
object ProjectsDataFactory {
    fun makeOwner(): OwnerModel {
        return OwnerModel(DataFactory.randomString(), DataFactory.randomString())
    }

    fun makeProject(): ProjectModel {
        return ProjectModel(DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomInt(),
                DataFactory.randomUuid(), makeOwner())
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid())
    }

    fun makeProjectsResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }
}