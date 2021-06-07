package academy.bangkit.capstonk.foories.core.di

import academy.bangkit.capstonk.foories.core.brain.ImageClassifier
import academy.bangkit.capstonk.foories.core.data.FooriesRepository
import academy.bangkit.capstonk.foories.core.data.source.local.LocalDataSource
import academy.bangkit.capstonk.foories.core.data.source.remote.RemoteDataSource
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import academy.bangkit.capstonk.foories.core.domain.usecase.*
import android.content.res.AssetManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<ScreeningUseCase> { ScreeningInteractor(get()) }
    single<HomeUseCase> { HomeInteractor(get()) }
    factory<DetectorUseCase> { DetectorInteractor(get(), get()) }
    single<IFooriesRepository> { FooriesRepository(get(), get()) }
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<AssetManager> { androidContext().assets }
    factory { ImageClassifier(get()) }
}