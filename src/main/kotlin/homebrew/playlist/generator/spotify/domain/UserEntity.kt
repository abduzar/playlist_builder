package homebrew.playlist.generator.spotify.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by sargisazaryan on 7/1/17.
 */
@Entity
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userDatabaseID: Long = 0

//    val userID: String
//        get() = userObject.id
//    val userURL: String
//        get() = userObject.href
//    val userCountry: String
//        get() = userObject.country
//    val userAvatarURL: String
//        get() = userObject.images[0].url
//    val userEmailAddress: String
//        get() = userObject.email
//    val userExternalURLs: ExternalUrls?
//        get() = userObject.externalUrls

}