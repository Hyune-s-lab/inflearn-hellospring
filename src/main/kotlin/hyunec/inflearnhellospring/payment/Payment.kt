package hyunec.inflearnhellospring.payment

import java.math.BigDecimal
import java.time.LocalDateTime

data class Payment(
    val orderId: Long,
    val currency: String,
    val foreignCurrencyAmount: BigDecimal,
    val exRate: BigDecimal,
    val convertedAmount: BigDecimal,
    val validUntil: LocalDateTime,
)
