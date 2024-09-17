package hyunec.inflearnhellospring.payment

import java.math.BigDecimal

interface ExRateProvider {
    fun getExRate(currency: String): BigDecimal
}
