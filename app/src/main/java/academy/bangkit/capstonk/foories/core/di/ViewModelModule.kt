package academy.bangkit.capstonk.foories.core.di

import academy.bangkit.capstonk.foories.presentation.detector.DetectorViewModel
import academy.bangkit.capstonk.foories.presentation.main.MainViewModel
import academy.bangkit.capstonk.foories.presentation.screening.ScreeningViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ScreeningViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { DetectorViewModel(get()) }
}