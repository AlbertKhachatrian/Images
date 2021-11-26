package aura.projects.images

import android.app.Application
import aura.projects.data.di.apiModule
import aura.projects.data.di.repoModule
import aura.projects.data.di.roomModule
import aura.projects.domain.di.domainModule
import aura.projects.images.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                apiModule,
                roomModule,
                repoModule,
                domainModule,
                appModule
            ))
        }
    }
}