package  am.xtech.app16.data.api

import am.xtech.app16.app.App
import am.xtech.app16.utils.LiveDataCallAdapterFactory
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

open class APIService {

    companion object Factory {

        private var apiInterface: ApiInterface

        init {
            apiInterface = buildRetrofit()
                .create(ApiInterface::class.java)
        }

        @JvmStatic
        @Synchronized
        fun getInstance(): ApiInterface {
            return apiInterface
        }

        private fun buildRetrofit() = Retrofit.Builder()
            .baseUrl("https://app16.x-tech.am/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(getHttpClient())
            .build()

        private fun getHttpClient(): OkHttpClient {

            val headerInterceptor: Interceptor = object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val builder =
                        chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .addHeader("Content-Type", "application/json")
//                            .addHeader("Content-Type", "application/json")


                    val request = builder.build()
                    return chain.proceed(request)
                }
            }


            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)

            //  .authenticator(NetAuthenticator())

            return httpClient.build()
        }
    }

//    class NetAuthenticator : Authenticator {
//
//        /**
//         * Authenticator for when the authToken need to be refresh and updated
//         * everytime we get a 401 error code
//         */
//        @Throws(IOException::class)
//        override fun authenticate(route: Route?, response: ModelCustomFilds): Request? {
//
//
//            var token: String? = null
//
//            val call: Call<LoggedInUser>? = apiInterface?.refreshToken()
//
//            try {
//                val responseCall: retrofit2.ModelCustomFilds<*> = call!!.execute()
//                val responseRequest: LoggedInUser? = responseCall.body() as LoggedInUser?
//
//                if (responseRequest != null && responseRequest.token.isNotEmpty()) {
//                    token = responseRequest.token
//                    App.sUserManager.saveAccessToken(App.sContext, responseRequest.token)
//                }
//
//
//            } catch (ex: Exception) {
//                Log.d("ERROR", "onResponse: $ex")
//            }
//
//            if (token == null) {
//
//                /**
//                 * Refresh token not valig
//                 * log out user
//                 */
//
////                App.sUserManager.saveAccessToken(App.sContext, "")
////
////                val i = Intent(App.sContext, SplashActivity::class.java)
////                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
////                App.sContext?.startActivity(i)
//
//                return null
//            }
//            return response.request.newBuilder()
//                .addHeader("Authorization", "Bearer $token")
//                .build()
//        }
//    }
}

