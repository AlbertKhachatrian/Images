package aura.projects.images.di

import aura.projects.images.ui.fragment.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainFragmentViewModel(get()) }
}