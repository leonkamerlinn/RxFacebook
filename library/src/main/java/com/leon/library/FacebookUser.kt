package com.leon.library


import com.google.gson.annotations.SerializedName

data class FacebookUser(
    val id: String,
    val name: String,
    val link: String,
    @SerializedName("age_range")
    val ageRange: AgeRange,
    val birthday: String,
    val context: Context,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val gender: String,
    @SerializedName("last_name")
    val lastName: String,
    val location: Location,
    val locale: String
) {
    data class Location(val id: String, val name: String)
    data class MutualFriend(val data: List<*>, val summary: Summary)
    data class MutualLike(val data: List<Any>, val summary: Summary)
    data class Summary(@SerializedName("total_count") val totalCount: Long)
    data class AgeRange(val min: Long)
    class Context(@SerializedName("mutual_friends") val mutualFriend: MutualFriend, @SerializedName("mutual_likes") val mutualLike: MutualLike, val id: String)
}
