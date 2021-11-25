package aura.projects.data.model.response

import com.squareup.moshi.Json

data class ImageResponse (
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "dimensions")
    val dimensions: DimensionsResponse?,
    @field:Json(name = "video")
    val video: Any?,
    @field:Json(name = "preview")
    val preview: String,
    @field:Json(name = "webp")
    val webp: String,
    @field:Json(name = "webp_preview")
    val webpPreview: String
)