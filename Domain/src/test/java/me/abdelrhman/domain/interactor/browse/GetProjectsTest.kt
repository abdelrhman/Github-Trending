package me.abdelrhman.domain.interactor.browse

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
class GetProjectsTest {

    private lateinit var getProjects: GetProjects
    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }


    @Test
    fun getProjectsCompletes() {
        stubGetProjects(Observable.just(ProjectsDataFactory.makeProjectsList(2)))
        val testObservable = getProjects.buildUseCaseObservable().test()
        testObservable.assertComplete()
    }


    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectsDataFactory.makeProjectsList(2)
        stubGetProjects(Observable.just(projects))
        val testObservable = getProjects.buildUseCaseObservable().test()
        testObservable.assertValue(projects)
    }


    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getProjects()).thenReturn(observable)
    }

}