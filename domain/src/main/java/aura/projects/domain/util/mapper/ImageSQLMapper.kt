package aura.projects.domain.util.mapper

import aura.projects.data.model.sql.ImageEntity
import aura.projects.domain.model.Image

object ImageSQLMapper: Mapper<Image, ImageEntity>() {
    override fun invoke(response: Image): ImageEntity = ImageEntity(url = response.url)
    fun fromEntity(entity: ImageEntity): Image = Image(entity.url)
}