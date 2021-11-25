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
    private var page = 0
    private val list = mutableListOf<Image>()

    override suspend fun invoke(): ActionResult<List<Image>> {
        return when(val data = feedRepository.getImages(++page)){
            is ActionResult.Success -> {
                data.data?.postCard?.forEach {
                    list.add(ImageMapper(PostCardMapper(it)))
                }
                ActionResult.Success(list.toList())
            }
            is ActionResult.Error -> {
                ActionResult.Error(data.errors)
            }
        }
    }
}