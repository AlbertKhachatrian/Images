package aura.projects.data.di


import android.app.Application
import androidx.room.Room
import aura.projects.core.BuildConfig
import aura.projects.data.dataservice.apiservice.FeedApiService
import aura.projects.data.dataservice.sqlservice.AppDatabase
import aura.projects.data.datastore.FeedRepository
import aura.projects.data.repository.FeedRepositoryImpl
import aura.projects.data.util.AuthenticationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .addInterceptor(AuthenticationInterceptor())
                    .build()
            )
            .build()
    }

    single { get<Retrofit>().create(FeedApiService::class.java) }

}

val roomModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "ArchDB")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single { provideDatabase(androidApplication()) }
    single { get<AppDatabase>().imagesDao() }
}

val repoModule = module {
    single<FeedRepository> { FeedRepositoryImpl(get()) }
}