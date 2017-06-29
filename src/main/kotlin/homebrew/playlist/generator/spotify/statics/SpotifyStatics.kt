package homebrew.playlist.generator.spotify.statics

import com.wrapper.spotify.Api
import com.wrapper.spotify.models.User
import homebrew.playlist.generator.configuration.ConfigurationFactory

/**
 * Created by sargisazaryan on 6/15/17.
 */
object SpotifyStatics {
    val factory = ConfigurationFactory()
    val callBack = factory.currentCallback()
    val CLIENT_ID = "d3f10ce3616049e0bddd86f9d3051af0"
    val CLIENT_SECRET = "b546a60532c14634be9f6b306bbda46b"
    lateinit var callBackCode: String
    lateinit var token: String
    lateinit var userToken: String
    lateinit var loggedInUser: User
    lateinit var api: Api
}