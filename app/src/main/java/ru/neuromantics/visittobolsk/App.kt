package ru.neuromantics.visittobolsk

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.neuromantics.visittobolsk.di.appModule
import ru.neuromantics.visittobolsk.di.dataModule
import ru.neuromantics.visittobolsk.di.domainModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
//        DynamicColors.applyToActivitiesIfAvailable(this)
        startKoin {
            androidContext(this@App)
            modules(appModule, domainModule, dataModule)
        }
    }
}