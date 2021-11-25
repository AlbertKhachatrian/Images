package aura.projects.domain.di

import aura.projects.domain.interactor.GetImagesInteractor
//import aura.projects.domain.paging.FeedPagingSource
import aura.projects.domain.usecase.GetImagesUseCase
import org.koin.dsl.module

val domainModule = module{
    single<GetImagesInteractor> { GetImagesUseCase(get()) }
//    single { FeedPagingSource(get()) }

}