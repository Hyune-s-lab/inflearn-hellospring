package hyunec.inflearnhellospring.learningtest

import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class ClockTest {
    @Test
    fun clock() {
        val clock = Clock.systemDefaultZone()

        val dt1 = LocalDateTime.now(clock)
        val dt2 = LocalDateTime.now(clock)

        dt2 shouldBeAfter dt1
    }

    @Test
    fun fixedClock() {
        val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

        val dt1 = LocalDateTime.now(clock)
        val dt2 = LocalDateTime.now(clock)

        dt2 shouldBe  dt1
    }
}
