package aura.projects.domain.di

import aura.projects.core.network.PackageService
import aura.projects.core.network.PackageServiceImpl
import aura.projects.domain.interactor.GetImagesInteractor
//import aura.projects.domain.paging.FeedPagingSource
import aura.projects.domain.usecase.GetImagesUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val domainModule = module{
//    single { FeedPagingSource(get()) }
    single<PackageService> { PackageServiceImpl(androidApplication()) }
    single<GetImagesInteractor> { GetImagesUseCase(get(), get(), get()) }
}