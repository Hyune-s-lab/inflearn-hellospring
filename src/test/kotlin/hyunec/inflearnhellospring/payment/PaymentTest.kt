package hyunec.inflearnhellospring.payment

import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class PaymentTest {
    @Test
    fun createPrepared() {
        val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

        val payment = Payment.createPrepared(
            1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1000), LocalDateTime.now(clock)
        )

        payment.convertedAmount shouldBeEqualComparingTo BigDecimal.valueOf(10000)
        payment.validUntil shouldBe LocalDateTime.now(clock).plusMinutes(30)
    }

    @Test
    fun isValid() {
        val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

        val payment = Payment.createPrepared(
            1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1000), LocalDateTime.now(clock)
        )

        payment.isValid(clock) shouldBe true
        payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES))) shouldBe false
    }
}
