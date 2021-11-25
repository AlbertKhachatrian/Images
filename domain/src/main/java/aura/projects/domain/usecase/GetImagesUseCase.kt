package aura.projects.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import aura.projects.domain.interactor.GetImagesInteractor
import aura.projects.domain.model.Image
import aura.projects.domain.paging.FeedPagingSource

class GetImagesUseCase(
    private val feedPagingSource: FeedPagingSource
): GetImagesInteractor {
    override fun invoke(): Pager<Int, Image> {
        return Pager(PagingConfig(1)){
            feedPagingSource
        }
    }
}