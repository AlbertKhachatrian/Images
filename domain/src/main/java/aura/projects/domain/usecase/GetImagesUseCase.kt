package aura.projects.domain.usecase

import aura.projects.core.network.ActionResult
import aura.projects.data.datastore.FeedRepository
import aura.projects.domain.interactor.GetImagesInteractor
import aura.projects.domain.model.Image
import aura.projects.domain.util.mapper.ImageMapper
import aura.projects.domain.util.mapper.PostCardMapper

class GetImagesUseCase(
    private val feedRepository: FeedRepository
): GetImagesInteractor {
    override suspend fun invoke(): ActionResult<List<Image>> {
        return when(val data = feedRepository.getImages()){
            is ActionResult.Success ->{
                val list = mutableListOf<Image>()
                data.data?.postCard?.forEach {
                    list.add(ImageMapper(PostCardMapper(it)))
                }
                ActionResult.Success(list)
            }
            is ActionResult.Error -> {
                ActionResult.Error(data.errors)
            }
        }
    }
}