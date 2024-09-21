package hyunec.inflearnhellospring.api

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.util.stream.Collectors

class SimpleApiExecutor: ApiExecutor {
    override fun execute(uri: URI): String {
        val connection = uri.toURL().openConnection() as HttpURLConnection
        val br = BufferedReader(InputStreamReader(connection.inputStream))
        val response = br.lines().collect(Collectors.joining("\n"))
        br.close()
        return response
    }
}
