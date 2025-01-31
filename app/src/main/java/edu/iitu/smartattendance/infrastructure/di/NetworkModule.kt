package edu.iitu.smartattendance.infrastructure.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.iitu.smartattendance.data.auth.AuthApi
import edu.iitu.smartattendance.infrastructure.network.createRetrofitBuilder
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

//    @Singleton
//    @Provides
//    fun provideMainOkHttpClient() : OkHttpClient {
//        return createOkHttpClientBuilder().build()
//    }

    @Singleton
    @Provides
    fun provideMainRetrofit(
//        okHttpClient: OkHttpClient,
        gson: Gson
    ) : Retrofit {
        return createRetrofitBuilder(
//            okHttpClient,
            gson, "http://192.168.1.3:8080/").build()
    }

    @Singleton
    @Provides
    fun provideYourApiService(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}