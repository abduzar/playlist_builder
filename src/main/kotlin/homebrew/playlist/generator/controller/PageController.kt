package homebrew.playlist.generator.controller

import homebrew.playlist.generator.spotify.connector.SpotifyConnector
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by sargisazaryan on 3/15/17.
 */
@Controller
class PageController {

    @RequestMapping(value = "/")
    fun mainPage():String{
        return "index"
    }

    @RequestMapping(value = "/login")
    fun spotifyLogin(){
        val connector = SpotifyConnector()
        connector.connect()

    }

    @RequestMapping(value = "/about_us")
    fun comingSoon(): String {
        return "about_us"
    }

}