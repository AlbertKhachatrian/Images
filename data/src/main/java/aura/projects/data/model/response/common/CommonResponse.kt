package aura.projects.data.model.response.common

import com.squareup.moshi.Json

data class CommonResponse<T>(
    @field:Json(name = "status")
    val status: Int,
    @field:Json(name = "status_msg")
    val statusMessage: String,
    @field:Json(name = "data")
    val data: T
)