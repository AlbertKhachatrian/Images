package aura.projects.data.util

import okhttp3.*

class AuthenticationInterceptor: Interceptor {
    private val AUTHORIZATION = "Authorization"
    private val ACCEPT_LANGUAGE = "Accept-Language"
    private val CONTENT_TYPE = "Content-Type"
    private val TOKEN = "Basic bW9iaWxlY2xpZW50OkxXdzhudzRjdlNvN0tkQlNWZ0Fq"
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request().newBuilder()
            .addHeader(AUTHORIZATION, TOKEN)
            .addHeader(ACCEPT_LANGUAGE, "ru_RU")
            .addHeader(CONTENT_TYPE, "application/json")
            .build())
}