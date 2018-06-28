package me.abdelrhman.data.store

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import me.abdelrhman.data.model.ProjectEntity
import me.abdelrhman.data.repository.ProjectsCache
import me.abdelrhman.data.test.factory.DataFactory
import me.abdelrhman.data.test.factory.ProjectFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Abdelrhman
 * on 6/27/18.
 */

@RunWith(JUnit4::class)
class ProjectsCacheDataStoreTest {

    private val cache = mock<ProjectsCache>()
    private val store = ProjectsCacheDataStore(cache)

    private fun stubProjectsCacheGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getProjects()).thenReturn(observable)
    }

    @Test
    fun getProjectsCompletes() {
        stubProjectsCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObservable = store.getProjects().test()
        testObservable.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetProjects(Observable.just(data))
        val testObservable = store.getProjects().test()
        testObservable.assertValue(data)
    }

    @Test
    fun getProjectsCallsCacheStore() {
        stubProjectsCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getProjects().test()
        verify(cache).getProjects()
    }

    @Test
    fun saveProjectsCompletes() {
        stubProjectsCacheSaveProjects(Completable.complete())
        stubProjectsLastCache(Completable.complete())
        val testCompletable = store.saveProjects(listOf(ProjectFactory.makeProjectEntity())).test()
        testCompletable.assertComplete()
    }

    @Test
    fun saveProjectsCallsCache() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheSaveProjects(Completable.complete())
        stubProjectsLastCache(Completable.complete())
        store.saveProjects(data).test()
        verify(cache).saveProjects(data)
    }


    @Test
    fun clearProjectsCompletes() {
        stubProjectsClearProjects(Completable.complete())
        val testCompletable = store.clearProjects().test()
        testCompletable.assertComplete()
    }

    @Test
    fun clearProjectsCallCache() {
        stubProjectsClearProjects(Completable.complete())
        store.clearProjects().test()
        verify(cache).clearProjects()
    }


    @Test
    fun getBookmarkedProjectsCompletes() {
        stubProjectsCacheGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObservable = store.getBookmarkedProjects().test()
        testObservable.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsCallsCache() {
        stubProjectsCacheGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getBookmarkedProjects().test()
        verify(cache).getBookmarkedProjects()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetBookmarkedProjects(Observable.just(data))
        val testObservable = store.getBookmarkedProjects().test()
        testObservable.assertValue(data)
    }


    @Test
    fun setProjectAsBookmarkedCompletes() {
        stubProjectsCacheBookmarkProject(Completable.complete())
        val completable = store.setProjectAsBookmarked(DataFactory.randomString()).test()
        completable.assertComplete()
    }

    @Test
    fun setProjectAsBookmarkedCallsCacheStore() {
        val data = DataFactory.randomString()
        stubProjectsCacheBookmarkProject(Completable.complete())
        store.setProjectAsBookmarked(data).test()
        verify(cache).setProjectAsBookmarked(data)
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        stubProjectsCacheNotBookmarkProject(Completable.complete())
        val completable = store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
        completable.assertComplete()
    }

    private fun stubProjectsCacheNotBookmarkProject(complete: Completable) {
        whenever(cache.setProjectAsNotBookmarked(any())).thenReturn(complete)
    }

    @Test
    fun setProjectAsNotBookmarkedCallsCacheStore() {
        val data = DataFactory.randomString()
        stubProjectsCacheNotBookmarkProject(Completable.complete())
        store.setProjectAsNotBookmarked(data).test()
        verify(cache).setProjectAsNotBookmarked(data)
    }

    private fun stubProjectsCacheBookmarkProject(complete: Completable) {
        whenever(cache.setProjectAsBookmarked(any())).thenReturn(complete)
    }

    private fun stubProjectsCacheGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getBookmarkedProjects()).thenReturn(observable)
    }

    private fun stubProjectsClearProjects(complete: Completable) {
        whenever(cache.clearProjects()).thenReturn(complete)
    }


    private fun stubProjectsLastCache(completable: Completable) {
        whenever(cache.setLastCashTime(any())).thenReturn(completable)
    }

    private fun stubProjectsCacheSaveProjects(completable: Completable) {
        whenever(cache.saveProjects(any())).thenReturn(completable)
    }

}