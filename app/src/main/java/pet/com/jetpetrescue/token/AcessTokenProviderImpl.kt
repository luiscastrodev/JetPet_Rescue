package pet.com.jetpetrescue.token

import pet.com.jetpetrescue.data.localstorage.StoragePref
import pet.com.jetpetrescue.data.network.models.AccessToken

class AcessTokenProviderImpl(
    private val storagePref: StoragePref
) : AcessTokenProvider {

    override suspend fun fetchAcessToken(): AccessToken? {
        return null
    }

    override fun refreshToken(): AccessToken? {
        return null
    }

    override fun saveToken(token: String?) {
        storagePref.saveToken(token)
    }

    override fun token(): String? {
        return storagePref.getToken()
    }

    override fun lock(): Any {
        return this
    }
}