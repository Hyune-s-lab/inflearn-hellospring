package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.data.OrderRepository
import hyunec.inflearnhellospring.order.Order
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.math.BigDecimal

class DataClient {}

fun main(args: Array<String>) {
    val beanFactory = AnnotationConfigApplicationContext(DataConfig::class.java)
    val orderRepository = beanFactory.getBean(OrderRepository::class.java)

    val transactionManager = beanFactory.getBean(JpaTransactionManager::class.java)

    try {
        TransactionTemplate(transactionManager).execute {
            val order = Order(no = "100", total = BigDecimal.TEN)
            orderRepository.save(order)

            println("### order: $order")

            val order2 = Order(no = "100", total = BigDecimal.TEN)
            orderRepository.save(order2)

            println("### order2: $order2")
        }
    } catch (e: DataIntegrityViolationException) {
        println("### 주문번호 중복 복구 작업")
    }
}
