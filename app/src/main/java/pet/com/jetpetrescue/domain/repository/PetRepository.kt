package pet.com.jetpetrescue.domain.repository

import pet.com.jetpetrescue.domain.models.Pet
import pet.com.jetpetrescue.utils.ResourceHolder

interface PetRepository {
    suspend fun getAnimal(page: Int): ResourceHolder<List<Pet>>
}