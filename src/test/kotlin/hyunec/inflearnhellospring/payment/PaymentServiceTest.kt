package hyunec.inflearnhellospring.payment

import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.BigDecimal.valueOf
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class PaymentServiceTest {
    private lateinit var clock: Clock

    @BeforeEach
    fun beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())
    }

    @Test
    fun convertedAmount() {
        testAmount(valueOf(500), valueOf(5000), this.clock)
        testAmount(valueOf(1000), valueOf(10000), this.clock)
        testAmount(valueOf(3000), valueOf(30000), this.clock)
    }

    @Test
    fun validUntil() {
        val paymentService = PaymentService(ExRateProviderStub(valueOf(1000)), clock)

        val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

        val now = LocalDateTime.now(this.clock)
        val expectedValidUntil = now.plusMinutes(30)

        payment.validUntil shouldBe expectedValidUntil
    }

    companion object {
        private fun testAmount(exRate: BigDecimal, convertedAmount: BigDecimal, clock: Clock) {
            val paymentService = PaymentService(ExRateProviderStub(exRate), clock)

            val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

            payment.exRate shouldBeEqualComparingTo exRate
            payment.convertedAmount shouldBeEqualComparingTo convertedAmount
        }
    }
}
