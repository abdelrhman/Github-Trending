package me.abdelrhman.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Abdelrhman
 * on 6/27/18.
 */
class ProjectsDataStoreFactoryTest {


    private val cashStore = mock<ProjectsCacheDataStore>()
    private val remoteStore = mock<ProjectsRemoteDataStore>()

    private val factory = ProjectsDataStoreFactory(cashStore, remoteStore)


    @Test
    fun getDataStoreReturnsRemoteStoreWhenDataIsExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }


    @Test
    fun getDataStoreReturnsRemoteStoreWhenNoProjectsCashed() {
        assertEquals(remoteStore, factory.getDataStore(false, true))
    }

    @Test
    fun getDataStoreReturnsCashStore() {
        assertEquals(cashStore, factory.getDataStore(true, false))
    }

    @Test
    fun getCashStoreReturnsCashStore() {
        assertEquals(cashStore, factory.getCacheDataStore())
    }

}