package me.abdelrhman.remote.mapper

import me.abdelrhman.data.model.ProjectEntity
import me.abdelrhman.remote.model.ProjectModel

/**
 * Created by Abdelrhman
 * on 6/28/18.
 */

open class ProjectsResponseModelMapper : ModelMapper<ProjectModel, ProjectEntity> {
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName,
                model.starCount.toString(), model.dateCreated, model.owner.ownerName,
                model.owner.ownerAvatar)
    }
}