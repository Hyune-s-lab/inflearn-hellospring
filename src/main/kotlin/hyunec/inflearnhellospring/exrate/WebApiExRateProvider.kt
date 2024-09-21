package hyunec.inflearnhellospring.exrate

import com.fasterxml.jackson.databind.ObjectMapper
import hyunec.inflearnhellospring.api.SimpleApiExecutor
import hyunec.inflearnhellospring.payment.ExRateProvider
import java.math.BigDecimal
import java.net.URI

class WebApiExRateProvider: ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/${currency}"
        return runApiForExRate(url, SimpleApiExecutor())
    }

    private fun runApiForExRate(url: String, apiExecutor: SimpleApiExecutor): BigDecimal {
        val uri = URI(url)
        val response = apiExecutor.execute(uri)
        return parseExRate(response)
    }

    private fun parseExRate(response: String): BigDecimal {
        val mapper = ObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)
        val exRate = data.rates["KRW"]!!
        return exRate
    }
}
