package hyunec.inflearnhellospring.order

import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
) : OrderService {
    override fun createOrder(no: String, total: BigDecimal): Order {
        val order = Order(no = no, total = total)
        orderRepository.save(order)
        return order
    }

    override fun createOrders(reqs: List<OrderReq>): List<Order> {
        return reqs.map { req -> createOrder(req.no, req.total) }
    }
}
