package homebrew.playlist.generator

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PlaylistGeneratorApplication

fun main(args: Array<String>) {
    SpringApplication.run(PlaylistGeneratorApplication::class.java, *args)
}
