package me.abdelrhman.domain.interactor.bookmark

import io.reactivex.Completable
import me.abdelrhman.domain.executor.PostExecutionThread
import me.abdelrhman.domain.interactor.CompletableUseCase
import me.abdelrhman.domain.repository.ProjectsRepository
import javax.inject.Inject

/**
 * Created by Abdelrhman
 * on 6/26/18.
 */
class BookmarkProject @Inject constructor(private val projectsRepository: ProjectsRepository,
                                          postExecutionThread: PostExecutionThread) : CompletableUseCase<BookmarkProject.Params>(postExecutionThread) {
    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")

        return projectsRepository.bookmarkProject(params.projectId)
    }


    data class Params(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}