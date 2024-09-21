package hyunec.inflearnhellospring.exrate

import hyunec.inflearnhellospring.api.ApiTemplate
import hyunec.inflearnhellospring.api.ErApiExtractor
import hyunec.inflearnhellospring.api.HttpClientApiExecutor
import hyunec.inflearnhellospring.payment.ExRateProvider
import java.math.BigDecimal


class WebApiExRateProvider : ExRateProvider {
    private val apiTemplate = ApiTemplate()

    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/${currency}"
        return apiTemplate.getExRate(url, HttpClientApiExecutor(), ErApiExtractor())
    }
}
