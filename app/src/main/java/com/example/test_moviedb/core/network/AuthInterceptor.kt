package com.example.test_moviedb.core.network

import com.example.test_moviedb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Add API Key as a query parameter for v3 endpoints
        val urlBuilder = originalUrl.newBuilder()
        if (BuildConfig.TMDB_API_KEY.isNotBlank()) {
            urlBuilder.addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
        }
        val url = urlBuilder.build()

        val requestBuilder = originalRequest.newBuilder()
            .url(url)
            .addHeader("Accept", "application/json")

        // Add Bearer Token if available (v4)
        if (BuildConfig.TMDB_BEARER_TOKEN.isNotBlank()) {
            requestBuilder.addHeader("Authorization", "Bearer ${BuildConfig.TMDB_BEARER_TOKEN}")
        }

        return chain.proceed(requestBuilder.build())
    }
}