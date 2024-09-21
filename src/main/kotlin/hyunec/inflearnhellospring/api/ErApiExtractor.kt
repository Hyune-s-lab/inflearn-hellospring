package hyunec.inflearnhellospring.api

import com.fasterxml.jackson.databind.ObjectMapper
import hyunec.inflearnhellospring.exrate.ExRateData
import java.math.BigDecimal

class ErApiExtractor: ExRateExtractor {
    override fun extract(response: String): BigDecimal {
        val mapper = ObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)
        return data.rates["KRW"]!!
    }
}
