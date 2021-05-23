package academy.bangkit.capstonk.foories.core.di

import academy.bangkit.capstonk.foories.core.config.Constants
import academy.bangkit.capstonk.foories.core.data.source.remote.network.FooriesApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(FooriesApi::class.java) }
}