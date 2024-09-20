package hyunec.inflearnhellospring.payment

import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime

class PaymentService(
    private val exRateProvider: ExRateProvider,
    private val clock: Clock,
) {
    fun prepare(orderId: Long, currency: String, foreignCurrencyAmount: BigDecimal): Payment {
        val exRate = exRateProvider.getExRate(currency)
        val convertedAmount = foreignCurrencyAmount.multiply(exRate)
        val validUntil = LocalDateTime.now(clock).plusMinutes(30)

        return Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil)
    }
}
