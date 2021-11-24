package aura.projects.data.model.response

import com.squareup.moshi.Json

data class PostCardResponse(
    @field:Json(name="id")
    val id: Int,
    @field:Json(name="name")
    val name: String,
    @field:Json(name="is_author")
    val isAuthor: Int,
    @field:Json(name="image")
    val image: ImageResponse,
)
