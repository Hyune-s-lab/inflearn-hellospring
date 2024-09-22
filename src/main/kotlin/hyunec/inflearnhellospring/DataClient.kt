package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.order.Order
import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

class DataClient {}

fun main(args: Array<String>) {
    val beanFactory = AnnotationConfigApplicationContext(DataConfig::class.java)
    val emf = beanFactory.getBean(EntityManagerFactory::class.java)

    val em = emf.createEntityManager()

    em.transaction.begin()

    val order = Order(no = "100", total = BigDecimal.TEN)
    em.persist(order)

    println("### order: $order")

    em.transaction.commit()
    em.close();
}
