package aura.projects.domain.util.mapper

import aura.projects.data.model.response.DimensionsResponse
import aura.projects.domain.model.Dimensions

object DimensionsMapper: Mapper<DimensionsResponse, Dimensions>() {
    override fun invoke(response: DimensionsResponse): Dimensions {
        return Dimensions(
            width = response.width,
            height = response.height
        )
    }
}