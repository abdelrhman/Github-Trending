package me.abdelrhman.remote.mapper

import me.abdelrhman.remote.test.factory.ProjectsDataFactory
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Abdelrhman
 * on 6/28/18.
 */
class ProjectsResponseModelMapperTest{
    private val mapper = ProjectsResponseModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = ProjectsDataFactory.makeProject()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.id, entity.id)
        assertEquals(model.name, entity.name)
        assertEquals(model.fullName, entity.fullName)
        assertEquals(model.starCount.toString(), entity.starCount)
        assertEquals(model.dateCreated, entity.dateCreated)
        assertEquals(model.owner.ownerName, entity.ownerName)
        assertEquals(model.owner.ownerAvatar, entity.ownerAvatar)
    }
}