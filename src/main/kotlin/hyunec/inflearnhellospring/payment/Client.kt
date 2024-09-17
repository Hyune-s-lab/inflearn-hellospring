package hyunec.inflearnhellospring.payment

import java.math.BigDecimal

class Client {}

fun main(args: Array<String>) {
    val objectFactory = ObjectFactory()
    val paymentService = objectFactory.paymentService()
    val payment = paymentService.prepare(100, "USD", BigDecimal(50.7))
    println(payment)
}
