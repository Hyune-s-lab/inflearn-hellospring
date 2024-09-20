package hyunec.inflearnhellospring.payment

import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime


data class Payment(
    val orderId: Long,
    val currency: String,
    val foreignCurrencyAmount: BigDecimal,
    val exRate: BigDecimal,
    val convertedAmount: BigDecimal,
    val validUntil: LocalDateTime,
) {
    fun isValid(clock: Clock): Boolean {
        return LocalDateTime.now(clock).isBefore(this.validUntil)
    }

    companion object {
        fun createPrepared(
            orderId: Long, currency: String, foreignCurrencyAmount: BigDecimal, exRate: BigDecimal, now: LocalDateTime,
        ): Payment {
            val convertedAmount = foreignCurrencyAmount.multiply(exRate)
            val validUntil = now.plusMinutes(30)

            return Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil)
        }
    }
}
