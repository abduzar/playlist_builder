package homebrew.playlist.generator.spotify.connector

import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import com.wrapper.spotify.Api
import com.wrapper.spotify.models.ClientCredentials
import interfaces.ResourceConnector
import homebrew.playlist.generator.spotify.connector.statics.SpotifyStatics
import statics.GlobalValues.log
import java.util.*


/**
 * Created by sargisazaryan on 6/9/17.
 */
class SpotifyConnector : ResourceConnector {

    override fun connect() {
        val api = Api.builder().clientId(SpotifyStatics.CLIENT_ID).clientSecret(SpotifyStatics.CLIENT_SECRET).build()
        val request = api.clientCredentialsGrant().build()
        SpotifyStatics.token = request.get().accessToken
        SpotifyStatics.api = api
        val responseFuture = request.async
        /* Add callbacks to handle success and failure */
        Futures.addCallback(responseFuture, object : FutureCallback<ClientCredentials> {
            override fun onSuccess(clientCredentials: ClientCredentials?) {
                /* The tokens were retrieved successfully! */
                log.info { "Successfully retrieved an access token! ${clientCredentials!!.accessToken}" }
                log.info { "The access token expires in ${clientCredentials!!.expiresIn} seconds" }

                /* Set access token on the Api object so that it's used going forward */
                api.setAccessToken(clientCredentials!!.accessToken)

                /* Please note that this flow does not return a refresh token.
                * That's only for the Authorization code flow */
            }

            override fun onFailure(throwable: Throwable) {
                /* An error occurred while getting the access token. This is probably caused by the client id or
                * client secret is invalid. */
            }
        })
    }

    override fun disconnect() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun makeAuthorizationURL(api: Api):String {
        /* Set the necessary scopes that the application will need from the user */
        val scopes = Arrays.asList("user-read-private", "user-read-email")

        /* Set a state. This is used to prevent cross site request forgeries. */
        val state = "someExpectedStateString"

        return api.createAuthorizeURL(scopes,state)
    }
}