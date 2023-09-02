package pet.com.jetpetrescue.data.network.mappers

import pet.com.jetpetrescue.data.network.models.Address
import pet.com.jetpetrescue.data.network.models.ApiAnimals
import pet.com.jetpetrescue.data.network.models.Contact
import pet.com.jetpetrescue.data.network.models.Photo
import pet.com.jetpetrescue.domain.models.Pet
import pet.com.jetpetrescue.domain.models.PetOwnerContacts
import pet.com.jetpetrescue.domain.models.PetPhoto

class PetApiMapperImpl : PetApiMapper<List<Pet>, ApiAnimals> {

    companion object {
        private const val EMPTY_DATA = "unknown"
    }

    override fun mapToDomain(apiEntity: ApiAnimals): List<Pet> {

        return apiEntity.animals.map { animal ->

            animal.run {
                Pet(
                    id = fomatData(id.toString()),
                    age = fomatData(age),
                    breeds = fomatData(breeds?.primary),
                    colors = fomatData(colors?.primary),
                    contact = formatContacts(contact),
                    description = fomatData(description),
                    distance = "",
                    gender = fomatData(gender),
                    name = fomatData(name),
                    photos = formatPhotos(photos),
                    size = fomatData(size),
                    species = fomatData(species),
                    status = fomatData(status),
                    tags = tags ?: emptyList(),
                    type = fomatData(type),
                    currentPage = apiEntity.pagination.currentPage?:0
                )
            }
        }

    }


    private fun formatContacts(contact: Contact?): PetOwnerContacts {
        return PetOwnerContacts(
            address = fomatData(formatAddress(contact?.address)),
            email = fomatData(contact?.email),
            phone = fomatData(contact?.phone)
        )
    }


    private fun formatAddress(address: Address?): String {
        val dot = "u25CF"
        if (address != null) {
            return "${address.city}$dot${address.country}"
        }

        return ""
    }

    private fun fomatData(data: String?): String {
        return data ?: EMPTY_DATA
    }

    private fun formatPhotos(photosList: List<Photo?>): List<PetPhoto> {
        return photosList?.map { photo ->
            PetPhoto(
                full = fomatData(photo?.full),
                large = fomatData(photo?.large),
                medium = fomatData(photo?.medium),
                small = fomatData(photo?.small),
            )
        } ?: listOf()
    }
}
