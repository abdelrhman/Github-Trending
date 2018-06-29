package me.abdelrhman.remote.service

import io.reactivex.Observable
import me.abdelrhman.remote.model.ProjectsResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Abdelrhman
 * on 6/28/18.
 */
interface GithubTrendingService {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String,
                           @Query("sort") sortBy: String,
                           @Query("order") order: String)
            : Observable<ProjectsResponseModel>
}