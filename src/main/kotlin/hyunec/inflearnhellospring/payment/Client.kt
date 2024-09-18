package hyunec.inflearnhellospring.payment

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

class Client {}

fun main(args: Array<String>) {
    val beanFactory = AnnotationConfigApplicationContext(ObjectFactory::class.java)
    val paymentService = beanFactory.getBean(PaymentService::class.java)

    val payment = paymentService.prepare(100, "USD", BigDecimal(50.7))
    println(payment)
}
