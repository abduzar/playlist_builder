package homebrew.playlist.generator.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

/**
 * Created by sargisazaryan on 6/29/17.
 */
@Configuration
class LocalDevConfiguration : Config {
    @Value(value = "\${url.callback}") override
    lateinit var callback: String

    @Value(value = "\${app.client.id") override
    lateinit var clientID: String

    @Value(value = "\${app.client.secret") override
    lateinit var clientSecret: String

}