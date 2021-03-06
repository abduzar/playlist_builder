package homebrew.playlist.generator

import homebrew.playlist.generator.configuration.ConfigurationProvider
import homebrew.playlist.generator.spotify.statics.SpotifyStatics
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PlaylistGeneratorApplication


fun main(args: Array<String>) {
    val context = SpringApplication.run(PlaylistGeneratorApplication::class.java, *args)
    val config = context.getBean(ConfigurationProvider::class.java)
    SpotifyStatics.config = config.getConfig()
}