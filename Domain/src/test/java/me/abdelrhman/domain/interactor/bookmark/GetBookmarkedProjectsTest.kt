package me.abdelrhman.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import me.abdelrhman.domain.executor.PostExecutionThread
import me.abdelrhman.domain.model.Project
import me.abdelrhman.domain.repository.ProjectsRepository
import me.abdelrhman.domain.test.ProjectsDataFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Abdelrhman
 * on 6/26/18.
 */
class GetBookmarkedProjectsTest {

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects

    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(projectsRepository, postExecutionThread)
    }


    @Test
    fun getBookmarkedProjectsCompletes() {
        stubGetBookmarkedProjects(Observable.just(ProjectsDataFactory.makeProjectsList(2)))
        val testObservable = getBookmarkedProjects.buildUseCaseObservable().test()
        testObservable.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val projects = ProjectsDataFactory.makeProjectsList(2)
        stubGetBookmarkedProjects(Observable.just(projects))
        val testObservable = getBookmarkedProjects.buildUseCaseObservable().test()
        testObservable.assertValue(projects)
    }


    private fun stubGetBookmarkedProjects(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getBookmarkedProjects()).thenReturn(observable)
    }


}