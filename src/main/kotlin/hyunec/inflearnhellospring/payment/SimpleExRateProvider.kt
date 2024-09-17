package hyunec.inflearnhellospring.payment

import java.math.BigDecimal

class SimpleExRateProvider: ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        if (currency == "USD") return BigDecimal.valueOf(1000);

        throw IllegalArgumentException("지원되지 않는 통화입니다");
    }
}
