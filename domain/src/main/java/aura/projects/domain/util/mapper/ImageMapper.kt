package aura.projects.domain.util.mapper

import aura.projects.core.BuildConfig
import aura.projects.data.model.response.ImageResponse
import aura.projects.domain.model.Image

object ImageMapper: Mapper<ImageResponse, Image>() {
    override fun invoke(response: ImageResponse): Image {
        return Image(
            url = BuildConfig.BASE_URL_IMAGE+response.url
        )
    }
}