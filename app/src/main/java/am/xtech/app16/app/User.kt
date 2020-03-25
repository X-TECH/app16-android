package am.xtech.app16.app

data class User(
    val deviceToken: String,
    val firstName: String,
    val lastName: String,
    val middleName: String
) {
    fun getFullName(): String {
        return "$firstName $lastName"
    }
}