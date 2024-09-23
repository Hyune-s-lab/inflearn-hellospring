package hyunec.inflearnhellospring.order

import java.math.BigDecimal

interface OrderService {
    fun createOrder(no: String, total: BigDecimal): Order
    fun createOrders(reqs: List<OrderReq>): List<Order>
}
