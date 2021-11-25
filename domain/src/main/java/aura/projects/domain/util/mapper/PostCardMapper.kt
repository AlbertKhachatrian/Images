package aura.projects.domain.util.mapper

import aura.projects.data.model.response.ImageResponse
import aura.projects.data.model.response.PostCardResponse

object PostCardMapper: Mapper<PostCardResponse, ImageResponse>() {
    override fun invoke(response: PostCardResponse): ImageResponse {
        return response.image
    }
}