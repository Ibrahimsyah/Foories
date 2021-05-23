package academy.bangkit.capstonk.foories

import academy.bangkit.capstonk.foories.core.di.appModule
import academy.bangkit.capstonk.foories.core.di.networkModule
import academy.bangkit.capstonk.foories.core.di.storageModule
import academy.bangkit.capstonk.foories.core.di.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appModule, viewModelModule, storageModule, networkModule)
        }
    }
}