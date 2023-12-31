package pet.com.jetpetrescue.data.network.retrofit

import pet.com.jetpetrescue.data.network.K.API_KEY
import pet.com.jetpetrescue.data.network.K.AUTH_END_POINT
import pet.com.jetpetrescue.data.network.K.BASE_END_END_POINT
import pet.com.jetpetrescue.data.network.K.CLIENT_ID
import pet.com.jetpetrescue.data.network.K.CLIENT_SECRET
import pet.com.jetpetrescue.data.network.K.SECRET_KEY
import pet.com.jetpetrescue.data.network.models.AccessToken
import pet.com.jetpetrescue.data.network.models.ApiAnimals
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PetFinderApiService {

    @GET(BASE_END_END_POINT)
    suspend fun getAnimals(
        @Query("page") page: Int
    ): ApiAnimals


    @POST(AUTH_END_POINT)
    @FormUrlEncoded
    suspend fun getAuthToken(
        @Field(CLIENT_ID) clientId: String = API_KEY,
        @Field(CLIENT_SECRET) clientSecret: String = SECRET_KEY,
        @Field("grant_type") grantType: String = "client_credentials"
    ): AccessToken
}