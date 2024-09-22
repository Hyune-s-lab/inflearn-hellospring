package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.data.OrderRepository
import hyunec.inflearnhellospring.order.Order
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

class DataClient {}

fun main(args: Array<String>) {
    val beanFactory = AnnotationConfigApplicationContext(DataConfig::class.java)
    val orderRepository = beanFactory.getBean(OrderRepository::class.java)

    val order = Order(no = "100", total = BigDecimal.TEN)
    orderRepository.save(order)

    println("### order: $order")

    val order2 = Order(no = "100", total = BigDecimal.TEN)
    orderRepository.save(order2)

    println("### order2: $order2")
}
