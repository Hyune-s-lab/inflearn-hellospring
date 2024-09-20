package hyunec.inflearnhellospring.payment

import hyunec.inflearnhellospring.TestPaymentConfig
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.date.shouldBeBefore
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TestPaymentConfig::class])
class PaymentServiceSpringTest(
    @Autowired private val paymentService: PaymentService,
) {
    @Test
    fun prepare() {
        val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

        payment.exRate shouldBe BigDecimal.valueOf(1_000)
        payment.convertedAmount shouldBe BigDecimal.valueOf(10_000)
        payment.validUntil shouldBeAfter LocalDateTime.now()
        payment.validUntil shouldBeBefore LocalDateTime.now().plusMinutes(30)
    }
}
