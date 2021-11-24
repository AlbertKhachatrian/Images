package aura.projects.data.model.response

import com.squareup.moshi.Json

data class DimensionsResponse(
    @field:Json(name = "width")
    val width: Int,
    @field:Json(name = "height")
    val height: Int
)