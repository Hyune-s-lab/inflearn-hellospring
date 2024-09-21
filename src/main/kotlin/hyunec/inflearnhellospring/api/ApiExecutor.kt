package hyunec.inflearnhellospring.api

import java.net.URI

interface ApiExecutor {
    fun execute(uri: URI): String
}
