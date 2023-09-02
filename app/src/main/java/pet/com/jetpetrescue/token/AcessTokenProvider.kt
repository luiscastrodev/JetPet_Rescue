package pet.com.jetpetrescue.token

import pet.com.jetpetrescue.data.network.models.AccessToken

interface AcessTokenProvider {

    suspend fun fetchAcessToken(): AccessToken?

    fun refreshToken(): AccessToken?

    fun saveToken(token: String?)

    fun token(): String?

    fun lock(): Any
}