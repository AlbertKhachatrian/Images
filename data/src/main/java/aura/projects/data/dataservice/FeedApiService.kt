package aura.projects.data.dataservice

import aura.projects.data.model.response.PostCardListResponse
import aura.projects.core.network.common.CommonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedApiService {

    @GET("lifestyle/feed")
    suspend fun getImages(@Query("page") page: Int): Response<CommonResponse<PostCardListResponse>>

}