package aura.projects.domain.util.mapper

import aura.projects.data.model.response.ImageResponse
import aura.projects.domain.model.Image

object ImageMapper: Mapper<ImageResponse, Image>() {
    override fun invoke(response: ImageResponse): Image {
        return Image(
            url = response.url,
            dimensions = DimensionsMapper(response.dimensions)
        )
    }
}