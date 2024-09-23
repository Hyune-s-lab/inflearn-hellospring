package hyunec.inflearnhellospring.order

import hyunec.inflearnhellospring.OrderConfig
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import javax.sql.DataSource
import kotlin.test.Test


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [OrderConfig::class])
class OrderServiceImplSpringTest(
    @Autowired private val orderService: OrderService,
    @Autowired private val dataSource: DataSource
) {
    @Test
    fun createOrder() {
        val order = orderService.createOrder("O100", BigDecimal.TEN)

        order.id!! shouldBeGreaterThan 0L
    }

    @Test
    fun createOrders() {
        val orderReqs = listOf(
            OrderReq("O200", BigDecimal.ONE),
            OrderReq("O201", BigDecimal.TWO)
        )

        val orders = orderService.createOrders(orderReqs)

        orders.size shouldBe 2
        orders.forEach { order -> order.id!! shouldBeGreaterThan 0L }
    }

    @Test
    fun createDuplicatedOrders() {
        val orderReqs = listOf(
            OrderReq("O300", BigDecimal.ONE),
            OrderReq("O300", BigDecimal.TWO)
        )

        shouldThrow<DataIntegrityViolationException> {
            orderService.createOrders(orderReqs)
        }

        val client = JdbcClient.create(dataSource)
        val count = client.sql("select count(*) from orders where no = 'O300'").query(Long::class.java).single()
        count shouldBe 0
    }
}
