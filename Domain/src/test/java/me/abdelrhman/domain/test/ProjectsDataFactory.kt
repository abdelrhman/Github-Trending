package me.abdelrhman.domain.test

import me.abdelrhman.domain.model.Project
import java.util.*

/**
 * Created by Abdelrhman
 * on 6/26/18.
 */
object ProjectsDataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() > 0.5
    }

    fun makeProject(): Project {
        return Project(randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomBoolean())
    }

    fun makeProjectsList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()

        repeat(count) {
            projects.add(makeProject())
        }

        return projects
    }
}