package hyunec.inflearnhellospring.payment

import java.math.BigDecimal
import java.time.LocalDateTime

class PaymentService {
    fun prepare(orderId: Long, currency: String, foreignCurrencyAmount: BigDecimal): Payment {
        // 환율 가져오기

        // 금액 계산

        // 유효 시간 계산

        return Payment(orderId, currency, foreignCurrencyAmount, BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now())
    }
}

fun main(args: Array<String>) {
    val paymentService = PaymentService()
    val payment = paymentService.prepare(100, "USD", BigDecimal(50.7))
    println(payment)
}
