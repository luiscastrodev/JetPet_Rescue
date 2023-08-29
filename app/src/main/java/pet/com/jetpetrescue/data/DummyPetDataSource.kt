package hoods.com.jetpetrescue.data

import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.data.model.Owner
import hoods.com.jetpetrescue.data.model.Pet

object DummyPetDataSource {

    private val owner = listOf(
        Owner(name = "Hulk", basicInfo = "Super hero", R.drawable.blue_dog),
        Owner(name = "Hulk", basicInfo = "Super hero", R.drawable.orange_dog),
        Owner(name = "Hulk", basicInfo = "Super hero", R.drawable.yellow_dog),
        Owner(name = "Hulk", basicInfo = "Super hero", R.drawable.white_dog),
        Owner(name = "Hulk", basicInfo = "Super hero", R.drawable.red_dog)
    )

    val dogList = listOf<Pet>(
        Pet(
            id = 0,
            name = "Julk",
            age = "Adult",
            gender =  "Male",
            color =  "Red",
            breed = "breed",
            location = "Toronto CA",
            image = R.drawable.blue_dog,
            description = "Loren ipsum is simply dummy",
            owner = owner[0]
        ) ,
        Pet(
            id = 0,
            name = "Julk",
            age = "Adult",
            gender =  "Male",
            color =  "Red",
            breed = "breed",
            location = "Toronto CA",
            image = R.drawable.orange_dog,
            description = "Loren ipsum is simply dummy",
            owner = owner[1]
        ),
        Pet(
            id = 0,
            name = "Julk",
            age = "Adult",
            gender =  "Male",
            color =  "Red",
            breed = "breed",
            location = "Toronto CA",
            image = R.drawable.yellow_dog,
            description = "Loren ipsum is simply dummy",
            owner = owner[2]
        ),
        Pet(
            id = 0,
            name = "Julk",
            age = "Adult",
            gender =  "Male",
            color =  "Red",
            breed = "breed",
            location = "Toronto CA",
            image = R.drawable.white_dog,
            description = "Loren ipsum is simply dummy",
            owner = owner[3]
        ),
        Pet(
            id = 0,
            name = "Julk",
            age = "Adult",
            gender =  "Male",
            color =  "Red",
            breed = "breed",
            location = "Toronto CA",
            image = R.drawable.red_dog,
            description = "Loren ipsum is simply dummy",
            owner = owner[4]
        )
    )
}