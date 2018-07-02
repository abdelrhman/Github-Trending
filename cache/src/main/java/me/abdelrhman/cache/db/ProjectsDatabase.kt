package me.abdelrhman.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import me.abdelrhman.cache.dao.CachedProjectsDao
import me.abdelrhman.cache.dao.ConfigDao
import me.abdelrhman.cache.model.CachedProject
import me.abdelrhman.cache.model.Config
import javax.inject.Inject

/**
 * Created by Abdelrhman
 * on 6/30/18.
 */

@Database(entities = [(CachedProject::class), (Config::class)], version = 1)
abstract class ProjectsDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjectsDao


    abstract fun cachedConfigDao(): ConfigDao

    private var INSTANCE: ProjectsDatabase? = null

    private val lock = Any()

    fun getInstance(context: Context): ProjectsDatabase {
        if (INSTANCE == null) {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, ProjectsDatabase::class.java, "projects.db").build()
                }
                return INSTANCE as ProjectsDatabase
            }
        }
        return INSTANCE as ProjectsDatabase
    }
}