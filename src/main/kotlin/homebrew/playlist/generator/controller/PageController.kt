package homebrew.playlist.generator.controller

import com.wrapper.spotify.models.SimplePlaylist
import homebrew.playlist.generator.service.UserDataService
import homebrew.playlist.generator.spotify.connector.SpotifyConnector
import homebrew.playlist.generator.spotify.domain.PlaylistMapping
import homebrew.playlist.generator.spotify.statics.SpotifyStatics
import homebrew.playlist.generator.spotify.statics.SpotifyStatics.api
import homebrew.playlist.generator.spotify.statics.SpotifyStatics.loggedInUser
import homebrew.playlist.generator.spotify.utils.getPlaylistByID
import homebrew.playlist.generator.spotify.utils.getPlaylistID
import homebrew.playlist.generator.spotify.utils.getPlaylistTracksByID
import homebrew.playlist.generator.spotify.utils.getUserFromURL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest

/**
 * Created by sargisazaryan on 3/15/17.
 */
@Controller
class PageController {
    lateinit var userPlaylists: List<SimplePlaylist>

    @Autowired
    lateinit var service: UserDataService

    val connector = SpotifyConnector()

    @RequestMapping(value = "/")
    fun mainPage(): String {
        return "index"
    }

    @RequestMapping(value = "/login", method = arrayOf(RequestMethod.GET))
    fun spotifyLogin(): String {
        connector.connect()
        return "redirect:${connector.makeAuthorizationURL(api)}"
    }

    @RequestMapping(value = "/callback", method = arrayOf(RequestMethod.GET))
    fun spotifyLoginCallback(@RequestParam("code") code: String): String {
        SpotifyStatics.callBackCode = code
        connector.getUser(code, service)
        return "redirect:/user"
    }

    @RequestMapping(value = "/user")
    fun userDetails(modelMap: ModelMap): String {
        modelMap.put("user", loggedInUser)
        val playlists = api.getPlaylistsForUser(loggedInUser.id).build()
        val items = playlists.get().items
        this.userPlaylists = items
        val map = mutableListOf<Triple<String, String, String>>()
        items.forEach {
            map.add(Triple(first = it.name, second = getUserFromURL(it.href)!!, third = getPlaylistID(it.href)!!))
        }
        modelMap.put("map", map)
        return "user_details"
    }

    @RequestMapping(value = "/playlist_mapping")
    fun playlistMappingDefinition(modelMap: ModelMap): String {
        modelMap.put("list", userPlaylists)
        userPlaylists[0].id
        return "playlist_mapping"
    }

    @RequestMapping(value = "/mapping", method = arrayOf(RequestMethod.GET))
    fun workoutGen(request: HttpServletRequest): String {
        val result = request.parameterMap
        val mappingList = mutableListOf<PlaylistMapping>()
        result.forEach { zoneID, playlistIDArray ->
            run {
                val playlistHref = userPlaylists.filter { it.id == playlistIDArray[0] }[0].href
                val tempMapping = PlaylistMapping(hrZone = zoneID, playlistID = playlistIDArray[0],
                        playlistOwnerID = getUserFromURL(playlistHref)!!)
                mappingList.add(tempMapping)
            }
        }
        SpotifyStatics.zoneMapping = mappingList
        return "mapping"
    }

    @RequestMapping(value = "/playlist/{user}/{id}", method = arrayOf(RequestMethod.GET))
    fun playlistTracks(@PathVariable user: String, @PathVariable id: String, modelMap: ModelMap): String {
        val playlist = getPlaylistByID(id = id, userID = user)
        val tracks = getPlaylistTracksByID(id = id, userID = user)
        modelMap.put("tracks", tracks)
        modelMap.put("playlist", playlist)
        return "playlist_details"
    }

}