package alektas.midic.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val headerName: String,
    private val headerValuePrefix: String = "",
    private val token: CharSequence
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.request().run {
        if (header(headerName) != null) {
            return@run chain.proceed(this)
        }

        val newRequest = newBuilder()
            .addHeader(headerName, "$headerValuePrefix $token")
            .build()
        chain.proceed(newRequest)
    }

}
