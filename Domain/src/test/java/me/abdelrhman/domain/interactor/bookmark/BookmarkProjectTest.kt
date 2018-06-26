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
class BookmarkProjectTest {

    private lateinit var bookmarkProject: BookmarkProject


    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(projectsRepository, postExecutionThread)
    }


    @Test
    fun bookmarkProjectComplete() {
        stubBookmarkProject(Completable.complete())
        val testCompletable = bookmarkProject.buildUseCaseCompletable(BookmarkProject.Params.forProject(ProjectsDataFactory.randomUuid())).test()
        testCompletable.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException() {
        stubBookmarkProject(Completable.complete())
        val testCompletable = bookmarkProject.buildUseCaseCompletable().test()
        testCompletable.assertComplete()
    }


    private fun stubBookmarkProject(completable: Completable) {
        whenever(projectsRepository.bookmarkProject(any())).thenReturn(completable)
    }

}