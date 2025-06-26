package com.dom.clean_mvvm_base.di

import android.app.Application
import android.content.Context
import com.dom.data.remote.ApiService
import com.dom.presentation.util.Constants
import com.dom.presentation.util.SharedPreferenceManager
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    private const val CONNECT_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        return Cache(application.cacheDir, 10L * 1024 * 1024)
    }

    @Provides
    @Singleton
    fun provideInterceptor(@ApplicationContext context: Context): Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                header("Accept", "application/json")
                // access token 이 있을 경우 여기서 헤더에 삽입
                val pref = SharedPreferenceManager(context)
                val token = pref.getString(Constants.PREF_ACCESS_TOKEN, "")
                val tokenType = pref.getString(Constants.PREF_TOKEN_TYPE, " ")
                if (token.isNotBlank() && tokenType.isNotBlank())
                    header("Authorization", "$tokenType $token")
            }.build())
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, interceptor: Interceptor, @ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cache(cache)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(interceptor)
            addInterceptor(HttpLoggingInterceptor {
                if (!it.startsWith("{") && !it.startsWith("[")) {
                    Timber.tag("OkHttp").d(it)
                } else {
                    try {
                        Timber.tag("OkHttp").d(
                            GsonBuilder().setPrettyPrinting().create().toJson(
                                JsonParser().parse(it)))
                    } catch (e: JsonSyntaxException) {
                        Timber.tag("OkHttp").d(it)
                    }
                }
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            //region 토큰 갱신 코드
//            authenticator(Authenticator { route, response ->
//
//                if (responseCount(response) >= 2) {
//                    return@Authenticator null
//                }
//
//                // token 갱신 로직 추가..
//                val pref = SharedPreferenceManager(context)
//                // 리프레쉬 api 에서 authorization 헤더가 추가 되지 않도록 삭제
//                pref.remove(Constants.PREF_TOKEN_TYPE)
//                try {
//                    // refresh api 호출 생성
//                    val call = provideApiService(OkHttpClient()).refreshToken(
//                        pref.getString(Constants.PREF_REFRESH_TOKEN, "")
//                    )
//                    return@Authenticator call.execute().let { res ->
//                        if (res.isSuccessful) {
//                            Timber.d("REFRESH TOKEN SUCCESS")
//                            res.body()?.let {
//                                pref.set(Constants.PREF_ACCESS_TOKEN, it.accessToken)
//                                pref.set(Constants.PREF_TOKEN_TYPE, it.tokenType)
//                                pref.set(Constants.PREF_REFRESH_TOKEN, it.refreshToken)
//
//                                response.request.newBuilder().header("Authorization", "${it.tokenType} ${it.accessToken}").build()
//                            }
//                        } else null
//                    }
//                } catch (e: IOException) {
//                    return@Authenticator null
//                }
//            })
            //endregion
        }.build()
    }

    private fun responseCount(response: Response): Int {
        var res: Response? = response
        var result = 1
        while ((res?.priorResponse.also { res = it }) != null) {
            result++
        }
        return result
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ApiService::class.java)
    }

}