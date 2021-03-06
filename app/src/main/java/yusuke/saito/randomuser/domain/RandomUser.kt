package yusuke.saito.randomuser.domain

data class RandomUser(
    val gender: String,
    val phone: String,
    val picture: RandomUserPicture,
    val email: String
)

data class RandomUserPicture(
    val thumbnail: String
)
