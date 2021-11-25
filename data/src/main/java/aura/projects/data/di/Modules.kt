package aura.projects.data.di


import aura.projects.data.BuildConfig
import aura.projects.data.dataservice.FeedApiService
import aura.projects.data.datastore.FeedRepository
import aura.projects.data.repository.FeedRepositoryImpl
import aura.projects.data.util.AuthenticationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

val repoModule = module {
    single<FeedRepository> { FeedRepositoryImpl(get()) }
}