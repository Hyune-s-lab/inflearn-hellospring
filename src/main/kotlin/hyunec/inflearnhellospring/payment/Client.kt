package hyunec.inflearnhellospring.payment

import hyunec.inflearnhellospring.payment.payment.PaymentService
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

class Client {}

fun main(args: Array<String>) {
    val beanFactory = AnnotationConfigApplicationContext(ObjectFactory::class.java)
    val paymentService = beanFactory.getBean(PaymentService::class.java)

    val payment1 = paymentService.prepare(100, "USD", BigDecimal(50.7))
    println("### payment1: $payment1")

    Thread.sleep(1000)

    val payment2 = paymentService.prepare(100, "USD", BigDecimal(50.7))
    println("### payment2: $payment2")

    Thread.sleep(2000)

    val payment3 = paymentService.prepare(100, "USD", BigDecimal(50.7))
    println("### payment2: $payment3")
}
