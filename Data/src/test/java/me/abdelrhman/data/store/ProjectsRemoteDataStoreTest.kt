package me.abdelrhman.data.store

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import me.abdelrhman.data.model.ProjectEntity
import me.abdelrhman.data.repository.ProjectsRemote
import me.abdelrhman.data.test.factory.DataFactory
import me.abdelrhman.data.test.factory.ProjectFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Abdelrhman
 * on 6/28/18.
 */
@RunWith(JUnit4::class)
class ProjectsRemoteDataStoreTest {


    val remote = mock<ProjectsRemote>()
    val store = ProjectsRemoteDataStore(remote)

    @Test
    fun getProjectsComplete() {
        stubProjectsRemoteGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsRemoteGetProjects(Observable.just(data))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }


    @Test
    fun getProjectsCallsRemote() {
        stubProjectsRemoteGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getProjects().test()
        verify(remote).getProjects()
    }


    @Test(expected = UnsupportedOperationException::class)
    fun clearProjectsThrowsException() {
        store.clearProjects()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedProjectsThrowsException() {
        store.getBookmarkedProjects()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsBookmarkedThrowsException() {
        store.setProjectAsBookmarked(DataFactory.randomString())
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsNotBookmarkedThrowsException() {
        store.setProjectAsNotBookmarked(DataFactory.randomString())
    }


    private fun stubProjectsRemoteGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(remote.getProjects()).thenReturn(observable)
    }

}