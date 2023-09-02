package pet.com.jetpetrescue.data.network.token

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pet.com.jetpetrescue.Graph
import pet.com.jetpetrescue.data.localstorage.StoragePref
import pet.com.jetpetrescue.data.network.models.AccessToken

class AcessTokenProviderImpl(
    private val storagePref: StoragePref
) : AcessTokenProvider {

    override suspend fun fetchAcessToken(): AccessToken?  = withContext(Dispatchers.IO){
        val acessToken = Graph.apiService.getAuthToken()
        saveToken(acessToken.accessToken)
        acessToken
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