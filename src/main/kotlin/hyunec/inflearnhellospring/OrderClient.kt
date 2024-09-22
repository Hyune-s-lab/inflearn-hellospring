package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.order.OrderService
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

class OrderClient {}

fun main(args: Array<String>) {
    val beanFactory = AnnotationConfigApplicationContext(OrderConfig::class.java)
    val orderService = beanFactory.getBean(OrderService::class.java)

    val order = orderService.createOrder("100", BigDecimal.TEN)
    println("### order: $order")
}
