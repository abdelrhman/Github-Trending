package me.abdelrhman.remote

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import me.abdelrhman.data.model.ProjectEntity
import me.abdelrhman.remote.mapper.ProjectsResponseModelMapper
import me.abdelrhman.remote.model.ProjectModel
import me.abdelrhman.remote.model.ProjectsResponseModel
import me.abdelrhman.remote.service.GithubTrendingService
import me.abdelrhman.remote.test.factory.ProjectsDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Abdelrhman
 * on 6/28/18.
 */

@RunWith(JUnit4::class)
class ProjectsRemoteImplTest {

    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectsRemoteImpl(service, mapper)


    @Test
    fun getProjectsCompletes() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(ProjectsDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(), ProjectsDataFactory.makeProjectEntity())
        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }



    @Test
    fun getProjectsCallsServer() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
                ProjectsDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
                ProjectsDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData() {
        val response = ProjectsDataFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepositories(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectsDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallsServerWithCorrectParameters() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
                ProjectsDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
                ProjectsDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }


    private fun stubGithubTrendingServiceSearchRepositories(observable: Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any())).thenReturn(observable)
    }

    private fun stubProjectsResponseModelMapperMapFromModel(model: ProjectModel,
                                                            entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
                .thenReturn(entity)
    }


}