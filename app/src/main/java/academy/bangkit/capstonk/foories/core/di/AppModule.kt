package academy.bangkit.capstonk.foories.core.di

import academy.bangkit.capstonk.foories.core.data.FooriesRepository
import academy.bangkit.capstonk.foories.core.data.source.local.LocalDataSource
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import academy.bangkit.capstonk.foories.core.domain.usecase.ScreeningInteractor
import academy.bangkit.capstonk.foories.core.domain.usecase.ScreeningUseCase
import org.koin.dsl.module

val appModule = module {
    single<ScreeningUseCase> { ScreeningInteractor(get()) }
    single<IFooriesRepository> { FooriesRepository(get()) }
    single { LocalDataSource(get()) }
}