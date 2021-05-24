package academy.bangkit.capstonk.foories.core.di

import academy.bangkit.capstonk.foories.core.config.Constants
import academy.bangkit.capstonk.foories.core.data.source.local.database.FooriesDatabase
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            FooriesDatabase::class.java, Constants.DB_NAME
        ).build()
    }

    single { get<FooriesDatabase>().fooriedDao() }
}