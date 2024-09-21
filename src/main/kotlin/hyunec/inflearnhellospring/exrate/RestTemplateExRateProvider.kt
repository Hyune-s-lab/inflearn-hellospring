package hyunec.inflearnhellospring.exrate

import hyunec.inflearnhellospring.payment.ExRateProvider
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

class RestTemplateExRateProvider(
        private val restTemplate: RestTemplate
) : ExRateProvider {

    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/$currency"

        return restTemplate.getForObject(url, ExRateData::class.java)!!.rates["KRW"]!!
    }
}
