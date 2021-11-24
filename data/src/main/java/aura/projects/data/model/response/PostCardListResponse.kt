package aura.projects.data.model.response

import com.squareup.moshi.Json

data class PostCardListResponse(
    @field:Json(name = "post_card")
    val postCard: List<PostCardResponse>
)
