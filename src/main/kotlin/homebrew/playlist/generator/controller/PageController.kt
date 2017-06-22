package homebrew.playlist.generator.controller

import homebrew.playlist.generator.spotify.connector.SpotifyConnector
import homebrew.playlist.generator.spotify.connector.statics.SpotifyStatics
import homebrew.playlist.generator.spotify.connector.statics.SpotifyStatics.api
import homebrew.playlist.generator.spotify.connector.statics.SpotifyStatics.loggedInUser
import homebrew.playlist.generator.spotify.utils.getPlaylistID
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by sargisazaryan on 3/15/17.
 */
@Controller
class PageController {
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
        connector.getUser(code)
        return "redirect:/user"
    }

    @RequestMapping(value = "/user")
    fun userDetails(modelMap: ModelMap): String {
        modelMap.put("user", loggedInUser)
        val playlists = api.getPlaylistsForUser(loggedInUser.id).build()
        val map = mutableMapOf<String, String>()
        playlists.get().items.forEach { map.put(it.name, getPlaylistID(it.href)!!) }
        modelMap.put("map", map.toList())
        return "user_details"
    }

    @RequestMapping(value = "/playlist/{id}")
    fun playlistTracks(@PathVariable playlistID: String, modelMap: ModelMap) {

    }

}