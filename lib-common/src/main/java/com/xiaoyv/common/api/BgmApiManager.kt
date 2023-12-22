package com.xiaoyv.common.api

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.Utils
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.xiaoyv.common.api.api.BgmJsonApi
import com.xiaoyv.common.api.api.BgmWebApi
import com.xiaoyv.common.api.converter.WebDocumentConverter
import com.xiaoyv.common.api.converter.WebHtmlConverter
import com.xiaoyv.common.api.interceptor.CommonInterceptor
import com.xiaoyv.common.api.interceptor.CookieInterceptor
import com.xiaoyv.common.api.interceptor.DouBanInterceptor
import com.xiaoyv.common.config.annotation.BgmPathType
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Class: [BgmApiManager]
 *
 * @author why
 * @since 11/18/23
 */
class BgmApiManager {

    private val cookieJar by lazy {
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(Utils.getApp()))
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(CommonInterceptor())
            .addInterceptor(DouBanInterceptor())
            .addNetworkInterceptor(CookieInterceptor())
            .apply {
                if (AppUtils.isAppDebug()) {
                    addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                }
            }
            .cookieJar(cookieJar)
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val noCookieHttpClient by lazy {
        httpClient.newBuilder()
            .cookieJar(CookieJar.NO_COOKIES)
            .build()
    }

    /**
     * 此 Retrofit 有 Cookie 持久化
     */
    private val webRetrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(WebHtmlConverter.create())
            .addConverterFactory(WebDocumentConverter.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .baseUrl(URL_BASE_WEB)
            .build()
    }

    /**
     * 此 Retrofit 没有 Cookie 持久化
     */
    private val apiRetrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(WebHtmlConverter.create())
            .addConverterFactory(WebDocumentConverter.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(noCookieHttpClient)
            .baseUrl(URL_BASE_API)
            .build()
    }

    private val bgmWebApi by lazy {
        webRetrofit.create(BgmWebApi::class.java)
    }

    private val bgmJsonApi by lazy {
        apiRetrofit.create(BgmJsonApi::class.java)
    }

    /**
     * 清空 Cookie
     */
    private fun resetCookie() {
        cookieJar.clear()
    }

    companion object {
        const val URL_BASE_WEB = "https://bangumi.tv"
        const val URL_BASE_API = "https://api.bgm.tv"

        private val instance by lazy { BgmApiManager() }

        val bgmJsonApi: BgmJsonApi
            get() = instance.bgmJsonApi

        val bgmWebApi: BgmWebApi
            get() = instance.bgmWebApi

        val httpClient
            get() = instance.httpClient

        fun readBgmCookie(): List<Cookie> {
            return instance.cookieJar.loadForRequest(
                URL_BASE_WEB.toHttpUrlOrNull() ?: return emptyList()
            )
        }

        fun resetCookie() {
            instance.resetCookie()
        }

        /**
         * 构建 Referer
         */
        fun buildReferer(@BgmPathType type: String, id: String): String {
            return when (type) {
                BgmPathType.TYPE_CHARACTER -> "$URL_BASE_WEB/character/$id"
                BgmPathType.TYPE_GROUP -> "$URL_BASE_WEB/group/$id"
                BgmPathType.TYPE_PERSON -> "$URL_BASE_WEB/person/$id"
                BgmPathType.TYPE_MESSAGE_BOX -> "$URL_BASE_WEB/pm/$id.chii"
                BgmPathType.TYPE_FRIEND -> "$URL_BASE_WEB/user/$id/friends"
                BgmPathType.TYPE_INDEX -> "$URL_BASE_WEB/index/$id"
                BgmPathType.TYPE_EP -> "$URL_BASE_WEB/subject/$id/ep"
                else -> URL_BASE_WEB
            }
        }

        /**
         * 构建 Referer
         */
        fun buildUserReferer(@BgmPathType type: String, id: String): String {
            return when (type) {
                BgmPathType.TYPE_FRIEND -> "$URL_BASE_WEB/user/$id/friends"
                BgmPathType.TYPE_INDEX -> "$URL_BASE_WEB/user/$id/index"
                else -> URL_BASE_WEB
            }
        }
    }
}