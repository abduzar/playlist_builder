package homebrew.playlist.generator

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PlaylistGeneratorApplication


fun main(args: Array<String>) {
    SpringApplication.run(PlaylistGeneratorApplication::class.java, *args)
}


//val context:ApplicationContext = SpringApplication.run(PlaylistGeneratorApplication::class.java, *args)
//var configFactory : ConfigurationFactory = context.getBean(ConfigurationFactory::class.java)
//SpotifyStatics.calllBack = configFactory.currentCallback()
//
//println(SpotifyStatics.calllBack)