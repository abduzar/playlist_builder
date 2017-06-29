package homebrew.playlist.generator.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

/**
 * Created by sargisazaryan on 6/29/17.
 */
@Configuration
class LocalDevConfiguration {
    @Value(value = "\${url.callback}")
    lateinit var callback: String
}