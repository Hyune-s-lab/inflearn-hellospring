package hyunec.inflearnhellospring.api

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HttpClientApiExecutor : ApiExecutor {
    override fun execute(uri: URI): String {
        val request = HttpRequest.newBuilder()
                .uri(uri).GET()
                .build()
        val httpClient = HttpClient.newBuilder()
                .build()

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body()
    }
}
