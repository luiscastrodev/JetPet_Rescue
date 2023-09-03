package pet.com.jetpetrescue.data.repository

import pet.com.jetpetrescue.data.network.mappers.PetApiMapper
import pet.com.jetpetrescue.data.network.models.ApiAnimals
import pet.com.jetpetrescue.data.network.retrofit.PetFinderApiService
import pet.com.jetpetrescue.domain.models.Pet
import pet.com.jetpetrescue.domain.repository.PetRepository
import pet.com.jetpetrescue.utils.ResourceHolder

class PetRepositoryImpl(
    private val apiService: PetFinderApiService,
    private val petApiMapper: PetApiMapper<List<Pet>, ApiAnimals>
) : PetRepository {
    override suspend fun getAnimal(page: Int): ResourceHolder<List<Pet>> {
        return try {
            val data = apiService.getAnimals(page)
            ResourceHolder.Success(petApiMapper.mapToDomain(data))
        } catch (e: Exception) {
            e.printStackTrace()
            ResourceHolder.Error(e.cause)
        }
    }
}