package me.abdelrhman.domain.interactor.bookmark

import io.reactivex.Observable
import me.abdelrhman.domain.executor.PostExecutionThread
import me.abdelrhman.domain.interactor.ObservableUseCase
import me.abdelrhman.domain.model.Project
import me.abdelrhman.domain.repository.ProjectsRepository
import javax.inject.Inject

/**
 * Created by Abdelrhman
 * on 6/26/18.
 */
class GetBookmarkedProjects @Inject constructor(private val projectsRepository: ProjectsRepository,
                                                postExecutionThread: PostExecutionThread) : ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {
    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }
}