package hyunec.inflearnhellospring.api

import java.math.BigDecimal

interface ExRateExtractor {
    fun extract(response: String): BigDecimal
}
