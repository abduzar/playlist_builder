package homebrew.playlist.generator.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

/**
 * Created by sargisazaryan on 6/24/17.
 */
@Configuration
class HerokuConfiguration {
    @Value(value = "\${url.callback}")
    lateinit var callback: String
}