package pet.com.jetpetrescue

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import pet.com.jetpetrescue.data.localstorage.StoragePref
import pet.com.jetpetrescue.data.network.K.BASE_URL
import pet.com.jetpetrescue.data.network.interceptors.AcessInterceptor
import pet.com.jetpetrescue.data.network.interceptors.AcessTokenAuthorization
import pet.com.jetpetrescue.data.network.mappers.PetApiMapper
import pet.com.jetpetrescue.data.network.mappers.PetApiMapperImpl
import pet.com.jetpetrescue.data.network.retrofit.PetFinderApiService
import pet.com.jetpetrescue.data.network.token.AcessTokenProvider
import pet.com.jetpetrescue.data.network.token.AcessTokenProviderImpl
import pet.com.jetpetrescue.data.repository.PetRepositoryImpl
import pet.com.jetpetrescue.domain.repository.PetRepository
import retrofit2.Retrofit

private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

object Graph {

    lateinit var tokenStoragePref: StoragePref
    lateinit var apiService: PetFinderApiService
    lateinit var acessTokenProvider: AcessTokenProvider
    lateinit var petRepository: PetRepository
    val apiMapper = PetApiMapperImpl()

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AcessInterceptor(acessTokenProvider))
            .authenticator(AcessTokenAuthorization(acessTokenProvider))
            .build()
    }

    fun provide(context: Context) {
        tokenStoragePref = StoragePref(context)
        acessTokenProvider = AcessTokenProviderImpl(tokenStoragePref)
        apiService = createPetFinderApiService()
        petRepository = PetRepositoryImpl(apiService, apiMapper)
    }

    private fun createPetFinderApiService(): PetFinderApiService {
        val contextType = "application/json".toMediaType()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory(contextType)
            ).build()
            .create(PetFinderApiService::class.java)
    }
}