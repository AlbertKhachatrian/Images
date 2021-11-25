package aura.projects.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import aura.projects.core.network.*
import aura.projects.data.dataservice.FeedApiService
import aura.projects.data.datastore.FeedRepository
import aura.projects.data.model.response.PostCardListResponse
import aura.projects.data.model.response.PostCardResponse
import java.lang.Exception

class FeedRepositoryImpl(
    private val feedApiService: FeedApiService
) : FeedRepository {
    private var page = 0

    override suspend fun getImages(): ActionResult<PostCardListResponse> {
        page++
        return makeApiCall({
            analyzeCommonResponse(feedApiService.getImages(page))
        })
    }
}