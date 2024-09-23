package hyunec.inflearnhellospring.order

import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.math.BigDecimal

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val transactionManager: PlatformTransactionManager
) {
    fun createOrder(no: String, total: BigDecimal): Order {
        val order = Order(no = no, total = total)
        orderRepository.save(order)
        return order
    }

    fun createOrders(reqs: List<OrderReq>): List<Order> {
        return TransactionTemplate(transactionManager).execute { _ ->
            reqs.map { req -> createOrder(req.no, req.total) }
        } ?: emptyList()
    }
}
