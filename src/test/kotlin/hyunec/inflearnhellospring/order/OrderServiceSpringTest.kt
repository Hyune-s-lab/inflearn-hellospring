package hyunec.inflearnhellospring.order

import hyunec.inflearnhellospring.OrderConfig
import io.kotest.matchers.longs.shouldBeGreaterThan
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import kotlin.test.Test


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [OrderConfig::class])
class OrderServiceSpringTest(
    @Autowired private val orderService: OrderService
) {
    @Test
    fun createOrders() {
        val order = orderService.createOrder("O100", BigDecimal.TEN)

        order.id!! shouldBeGreaterThan 0L
    }
}
