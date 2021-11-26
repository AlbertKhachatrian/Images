package aura.projects.domain.usecase

import aura.projects.core.network.ActionResult
import aura.projects.core.network.CallException
import aura.projects.core.network.PackageService
import aura.projects.data.dataservice.sqlservice.AppDatabase
import aura.projects.data.dataservice.sqlservice.ImagesDao
import aura.projects.data.datastore.FeedRepository
import aura.projects.data.model.sql.ImageEntity
import aura.projects.domain.interactor.GetImagesInteractor
import aura.projects.domain.model.Image
import aura.projects.domain.util.mapper.ImageMapper
import aura.projects.domain.util.mapper.ImageSQLMapper
import aura.projects.domain.util.mapper.PostCardMapper

class GetImagesUseCase(
    private val feedRepository: FeedRepository,
    private val imagesDao: ImagesDao,
    private val packageService: PackageService
): GetImagesInteractor {
    private var page = 0
    private val list = mutableListOf<ImageEntity>()
    private var blocked: Boolean = false

    override suspend fun invoke(): ActionResult<List<Image>>? {
        if(!blocked) {
            blocked = true
            return if (packageService.checkInternetConnection()) {
                when (val data = feedRepository.getImages(++page)) {
                    is ActionResult.Success -> {
                        blocked = false
                        data.data?.postCard?.forEach {
                            list.add(ImageSQLMapper(ImageMapper(PostCardMapper(it))))
                        }
                        imagesDao.deleteAll()
                        imagesDao.insertAll(list)
                        val finalList = mutableListOf<Image>()
                        imagesDao.getAll()?.forEach {
                            finalList.add(ImageSQLMapper.fromEntity(it))
                        }
                        ActionResult.Success(finalList)
                    }
                    is ActionResult.Error -> {
                        blocked = false
                        val images = imagesDao.getAll()
                        if (images.isNullOrEmpty()) {
                            ActionResult.Error(data.errors)
                        } else {
                            val finalList = mutableListOf<Image>()
                            images.forEach {
                                finalList.add(ImageSQLMapper.fromEntity(it))
                            }
                            ActionResult.Success(finalList)
                        }
                    }
                }
            } else {
                blocked = false
                val images = imagesDao.getAll()
                if (images.isNullOrEmpty()) {
                    ActionResult.Error(CallException(12345, "Check internet connection"))
                } else {
                    val finalList = mutableListOf<Image>()
                    images.forEach {
                        finalList.add(ImageSQLMapper.fromEntity(it))
                    }
                    ActionResult.Success(finalList)
                }
            }
        }else{
            return null
        }
    }
}