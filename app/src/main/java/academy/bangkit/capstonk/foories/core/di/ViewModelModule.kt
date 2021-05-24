package academy.bangkit.capstonk.foories.core.di

import academy.bangkit.capstonk.foories.presentation.main.MainViewModel
import academy.bangkit.capstonk.foories.presentation.screening.ScreeningViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { ScreeningViewModel(get()) }
    single { MainViewModel(get()) }
}