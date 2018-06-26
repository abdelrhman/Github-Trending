package me.abdelrhman.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import me.abdelrhman.domain.executor.PostExecutionThread
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
class UnBookmarkProjectTest {

    private lateinit var unBookmarkProject: UnBookmarkProject


    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        unBookmarkProject = UnBookmarkProject(projectsRepository, postExecutionThread)
    }


    @Test
    fun unBookmarkProjectComplete() {
        stubUnBookmarkProject(Completable.complete())
        val testCompletable = unBookmarkProject.buildUseCaseCompletable(UnBookmarkProject.Params.forProject(ProjectsDataFactory.randomUuid())).test()
        testCompletable.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unBookmarkProjectThrowsException() {
        stubUnBookmarkProject(Completable.complete())
        val testCompletable = unBookmarkProject.buildUseCaseCompletable().test()
        testCompletable.assertComplete()
    }


    private fun stubUnBookmarkProject(completable: Completable) {
        whenever(projectsRepository.unbookmarkProject(any())).thenReturn(completable)
    }

}