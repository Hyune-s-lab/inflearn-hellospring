package hyunec.inflearnhellospring.api

import java.math.BigDecimal
import java.net.URI

class ApiTemplate {
    fun getExRate(
            url: String,
            apiExecutor: HttpClientApiExecutor,
            exRateExtractor: ErApiExtractor,
    ): BigDecimal {
        val uri = URI(url)
        val response = apiExecutor.execute(uri)
        return exRateExtractor.extract(response)
    }
}
