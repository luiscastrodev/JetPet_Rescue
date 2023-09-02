package pet.com.jetpetrescue

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import pet.com.jetpetrescue.data.localstorage.StoragePref
import pet.com.jetpetrescue.data.network.K.BASE_URL
import pet.com.jetpetrescue.data.network.retrofit.PetFinderApiService
import retrofit2.Retrofit

private val json = Json { ignoreUnknownKeys = true }

object Graph {

    lateinit var tokenStoragePref: StoragePref
    lateinit var apiService: PetFinderApiService

    fun provide(context: Context) {
        tokenStoragePref = StoragePref(context)
        apiService = createPetFinderApiService()
    }

    private fun createPetFinderApiService(): PetFinderApiService {
        val contextType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory(contextType)
            ).build()
            .create(PetFinderApiService::class.java)
    }
}