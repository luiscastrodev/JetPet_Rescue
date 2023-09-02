package pet.com.jetpetrescue.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import pet.com.jetpetrescue.data.network.token.AcessTokenProvider

class AcessInterceptor(
    private val acessTokenProvider: AcessTokenProvider
) : Interceptor {

    companion object {
        const val TAG = "myApp"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }

    private fun Request.signedRequest(): Request {

        return newBuilder()
            .addHeader("Authorization", "Bearer ${acessTokenProvider.token()}")
            .build()
    }

}