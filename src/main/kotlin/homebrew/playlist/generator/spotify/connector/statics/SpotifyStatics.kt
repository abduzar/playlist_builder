package homebrew.playlist.generator.spotify.connector.statics

import com.wrapper.spotify.Api

/**
 * Created by sargisazaryan on 6/15/17.
 */
object SpotifyStatics {
    val host = "localhost:8080"
    val CLIENT_ID = "d3f10ce3616049e0bddd86f9d3051af0"
    val CLIENT_SECRET = "b546a60532c14634be9f6b306bbda46b"
    lateinit var token: String
    lateinit var api: Api
}