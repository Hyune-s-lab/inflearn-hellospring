package hyunec.inflearnhellospring.payment

import hyunec.inflearnhellospring.exrate.WebApiExRateProvider
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.date.shouldBeBefore
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class PaymentServiceTest {
    @Test
    fun prepare() {
        val paymentService = PaymentService(WebApiExRateProvider())
        val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

        payment.exRate shouldNotBe null
        payment.convertedAmount shouldBe payment.exRate.multiply(payment.foreignCurrencyAmount)
        payment.validUntil shouldBeAfter LocalDateTime.now()
        payment.validUntil shouldBeBefore LocalDateTime.now().plusMinutes(30)
    }
}
