package aura.projects.data.datastore

import aura.projects.core.network.ActionResult
import aura.projects.data.model.response.PostCardListResponse

interface FeedRepository {
    suspend fun getImages(): ActionResult<PostCardListResponse>
}