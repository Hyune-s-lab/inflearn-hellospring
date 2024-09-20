package hyunec.inflearnhellospring.payment

import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.date.shouldBeBefore
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class PaymentServiceTest {
    @Test
    fun prepare() {
        val paymentService = PaymentService(ExRateProviderStub(BigDecimal.valueOf(500)))
        val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

        payment.exRate shouldBe BigDecimal.valueOf(500)
        payment.convertedAmount shouldBe BigDecimal.valueOf(5000)
        payment.validUntil shouldBeAfter LocalDateTime.now()
        payment.validUntil shouldBeBefore LocalDateTime.now().plusMinutes(30)
    }
}
