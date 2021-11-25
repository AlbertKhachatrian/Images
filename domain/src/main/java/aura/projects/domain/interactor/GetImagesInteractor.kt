package aura.projects.domain.interactor

import androidx.paging.Pager
import aura.projects.domain.model.Image

interface GetImagesInteractor {
    operator fun invoke(): Pager<Int, Image>
}