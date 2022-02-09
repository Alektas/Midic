package alektas.midic.di

import alektas.common.data.remote.owlbot.OwlbotApi
import alektas.midic.BuildConfig
import alektas.midic.data.remote.config.ApiConfig.BASE_URL_OWLBOT_API
import alektas.midic.data.remote.config.ApiConfig.HEADER_NAME_AUTHORIZATION
import alektas.midic.data.remote.config.ApiConfig.HEADER_VALUE_PREFIX_AUTHORIZATION
import alektas.midic.data.remote.config.ApiConfig.HTTP_TIMEOUT
import alektas.midic.data.remote.interceptors.AuthInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
interface NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideOwlbotApi(retrofit: Retrofit): OwlbotApi = retrofit.create(OwlbotApi::class.java)

        @Provides
        @Singleton
        fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL_OWLBOT_API)
            .client(client)
            .build()

        @Provides
        @Singleton
        fun provideGson(): Gson = GsonBuilder()
            .setLenient()
            .create()

        @Provides
        @Singleton
        fun provideOkhttpClientBuilder(authInterceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                    addInterceptor(loggingInterceptor)
                }
            }
            .addInterceptor(authInterceptor)
            .connectTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()

        @Provides
        @Singleton
        fun provideOwlbotAuthInterceptor(): Interceptor = AuthInterceptor(
            headerName = HEADER_NAME_AUTHORIZATION,
            headerValuePrefix = HEADER_VALUE_PREFIX_AUTHORIZATION,
            token = BuildConfig.OWLBOT_API_TOKEN
        )
    }

}
