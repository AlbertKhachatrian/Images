package aura.projects.data.util

import okhttp3.*

class AuthenticationInterceptor: Interceptor {
    private val AUTHORIZATION = "Authorization"
    private val TOKEN = "Basic bW9iaWxlY2xpZW50OkxXdzhudzRjdlNvN0tkQlNWZ0Fq"
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(chain.request().newBuilder().addHeader(AUTHORIZATION, TOKEN).build())
}