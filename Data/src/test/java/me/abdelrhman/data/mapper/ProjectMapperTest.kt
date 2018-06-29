package me.abdelrhman.data.mapper

import me.abdelrhman.data.model.ProjectEntity
import me.abdelrhman.data.test.factory.ProjectFactory
import me.abdelrhman.domain.model.Project
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Abdelrhman
 * on 6/27/18.
 */
class ProjectMapperTest{

    private val mapper = ProjectMapper()

    @Test
    fun mapFromEntityMapsData(){
        val entity = ProjectFactory.makeProjectEntity()
        val model = mapper.mapFromEntity(entity)
        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData(){
        val model = ProjectFactory.makeProject()
        val entity = mapper.mapToEntity(model)
        assertEqualData(entity, model)
    }



    private fun assertEqualData(entity: ProjectEntity,
                                model: Project){
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.fullName, model.fullName)
        assertEquals(entity.starCount, model.starCount)
        assertEquals(entity.dateCreated, model.dateCreated)
        assertEquals(entity.ownerName, model.ownerName)
        assertEquals(entity.ownerAvatar, model.ownerAvatar)
        assertEquals(entity.isBookmarked, model.isBookmarked)

    }
}