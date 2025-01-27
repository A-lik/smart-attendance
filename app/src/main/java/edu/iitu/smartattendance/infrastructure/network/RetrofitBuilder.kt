package edu.iitu.smartattendance.infrastructure.network

import com.google.gson.Gson
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections
import java.util.concurrent.TimeUnit

private const val DEFAULT_TIMEOUT_SECONDS = 10L

internal fun createOkHttpClientBuilder(): OkHttpClient.Builder {
    val client = OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .connectionSpecs(Collections.singletonList(provideConnectionSpec()))
//        .addInterceptor(LogInt)
//
//    if (BuildConfig.DEBUG) {
//        val logging = LogInterceptor().create()
//        client.addInterceptor(logging)
//    }

    return client
}

internal fun createRetrofitBuilder(
    okHttpClient: OkHttpClient, gson: Gson,
    baseUrl: String
): Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
}

private fun provideConnectionSpec(): ConnectionSpec {
    return ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
        .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_3)
        .cipherSuites(
            // TLS 1_2
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
            CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
            CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256,
            CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256,
            // TLS 1_3
            CipherSuite.TLS_AES_128_GCM_SHA256,
            CipherSuite.TLS_AES_256_GCM_SHA384,
            CipherSuite.TLS_CHACHA20_POLY1305_SHA256
        )
        .build()
}