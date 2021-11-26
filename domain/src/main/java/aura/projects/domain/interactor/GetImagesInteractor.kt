package aura.projects.domain.interactor

import androidx.paging.Pager
import aura.projects.core.network.ActionResult
import aura.projects.domain.model.Image

interface GetImagesInteractor {
    suspend operator fun invoke(): ActionResult<List<Image>>?
}