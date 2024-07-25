package yermakov.oleksii.redditnewsfeed.domain

import com.google.gson.annotations.SerializedName

data class RedditPost(
    @SerializedName("title")
    val postName: String,
    @SerializedName("author")
    val postAuthor: String,
    @SerializedName("num_comments")
    val countOfComments: Int,
    @SerializedName("thumbnail")
    val thumbnail: String?
)

fun dummyRedditPostList(): List<RedditPost> {
    return listOf(
        RedditPost(
            postName = "Is Cybersecurity saturated?",
            postAuthor = "Character-Ad-618",
            countOfComments = 328,
            thumbnail = "https://assets.everspringpartners.com/dims4/default/0fc1868/2147483647/strip/true/crop/620x250+0+0/resize/620x250!/quality/90/?url=http%3A%2F%2Feverspring-brightspot.s3.us-east-1.amazonaws.com%2F68%2F50%2F50bc96eb4ea998f053c9a6b9e914%2Fadobestock-245636933-620x250.jpg"
        ),
        RedditPost(
            postName = "Best resources for learning ethical hacking?",
            postAuthor = "EthicalHacker101",
            countOfComments = 152,
            thumbnail = null
        ),
        RedditPost(
            postName = "How to protect yourself from phishing attacks?",
            postAuthor = "SecurityExpert",
            countOfComments = 85,
            thumbnail = "https://imageio.forbes.com/specials-images/imageserve/65cb9e725c84496aeccd33c1/Ransomware-Cyber-Security-Email-Phishing-Encrypted-Technology--Digital-Information/960x0.jpg?format=jpg&width=960"
        ),RedditPost(
            postName = "How to protect yourself from phishing attacks?",
            postAuthor = "SecurityExpert",
            countOfComments = 10000,
            thumbnail = null
        ),RedditPost(
            postName = "How to protect yourself from phishing attacks?",
            postAuthor = "SecurityExpert",
            countOfComments = 2,
            thumbnail = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGhyMjtyWCbbD9aMyFj-evx_AB9JllEvPs5w&s"
        ),
    )
}