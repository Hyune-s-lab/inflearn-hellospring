package hyunec.inflearnhellospring.exrate

import hyunec.inflearnhellospring.api.ErApiExtractor
import hyunec.inflearnhellospring.api.SimpleApiExecutor
import hyunec.inflearnhellospring.payment.ExRateProvider
import java.math.BigDecimal
import java.net.URI

class WebApiExRateProvider: ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/${currency}"
        return runApiForExRate(url, SimpleApiExecutor(), ErApiExtractor())
    }

    private fun runApiForExRate(
        url: String,
        apiExecutor: SimpleApiExecutor,
        exRateExtractor: ErApiExtractor,
    ): BigDecimal {
        val uri = URI(url)
        val response = apiExecutor.execute(uri)
        return exRateExtractor.extract(response)
    }
}
