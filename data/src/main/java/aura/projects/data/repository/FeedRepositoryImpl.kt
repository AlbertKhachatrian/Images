package aura.projects.data.repository

import aura.projects.core.network.*
import aura.projects.data.dataservice.apiservice.FeedApiService
import aura.projects.data.dataservice.sqlservice.AppDatabase
import aura.projects.data.datastore.FeedRepository
import aura.projects.data.model.response.PostCardListResponse

class FeedRepositoryImpl(
    private val feedApiService: FeedApiService,
) : FeedRepository {

    override suspend fun getImages(page: Int): ActionResult<PostCardListResponse> {
        return makeApiCall({
            analyzeCommonResponse(feedApiService.getImages(page))
        })
    }
}