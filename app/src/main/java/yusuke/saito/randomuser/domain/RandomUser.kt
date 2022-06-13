package yusuke.saito.randomuser.domain

data class RandomUser(
    val gender: String,
    val phone: String,
    val picture: RandomUserPicture
)

data class RandomUserPicture(
    val thumbnail: String
)

