package homebrew.playlist.generator.spotify.statics

import com.wrapper.spotify.Api
import com.wrapper.spotify.models.User
import homebrew.playlist.generator.configuration.Config
import homebrew.playlist.generator.spotify.domain.PlaylistMapping

/**
 * Created by sargisazaryan on 6/15/17.
 */
object SpotifyStatics {
    lateinit var config: Config
    lateinit var callBackCode: String
    lateinit var token: String
    lateinit var userToken: String
    lateinit var loggedInUser: User
    lateinit var api: Api
    lateinit var zoneMapping: List<PlaylistMapping>
}