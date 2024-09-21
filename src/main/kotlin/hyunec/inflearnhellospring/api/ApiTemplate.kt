package hyunec.inflearnhellospring.api

import java.math.BigDecimal
import java.net.URI

class ApiTemplate {
    private val defaultApiExecutor = HttpClientApiExecutor()
    private val defaultAExRateExtractor = ErApiExtractor()

    fun getExRate(url: String): BigDecimal {
        return getExRate(url, defaultApiExecutor, defaultAExRateExtractor)
    }

    fun getExRate(
            url: String,
            apiExecutor: ApiExecutor,
    ): BigDecimal {
        return getExRate(url, apiExecutor, defaultAExRateExtractor)
    }

    fun getExRate(
            url: String,
            exRateExtractor: ExRateExtractor,
    ): BigDecimal {
        return getExRate(url, defaultApiExecutor, exRateExtractor)
    }

    fun getExRate(
            url: String,
            apiExecutor: ApiExecutor,
            exRateExtractor: ExRateExtractor,
    ): BigDecimal {
        val uri = URI(url)
        val response = apiExecutor.execute(uri)
        return exRateExtractor.extract(response)
    }
}
