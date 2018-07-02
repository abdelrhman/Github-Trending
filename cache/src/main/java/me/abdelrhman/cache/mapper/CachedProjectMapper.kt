package me.abdelrhman.cache.mapper

import me.abdelrhman.cache.model.CachedProject
import me.abdelrhman.data.model.ProjectEntity

/**
 * Created by Abdelrhman
 * on 6/30/18.
 */


class CachedProjectMapper : CacheMapper<CachedProject, ProjectEntity> {

    override fun mapFromCached(type: CachedProject): ProjectEntity {
        return ProjectEntity(type.id, type.name, type.fullName, type.starCount,
                type.dateCreated, type.ownerName, type.ownerAvatar,
                type.isBookmarked)
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return CachedProject(type.id, type.name, type.fullName, type.starCount,
                type.dateCreated, type.ownerName, type.ownerAvatar,
                type.isBookmarked)
    }

}