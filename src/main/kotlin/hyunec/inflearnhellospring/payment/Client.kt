package hyunec.inflearnhellospring.payment

import java.math.BigDecimal

class Client {

}

fun main(args: Array<String>) {
//    val paymentService = WebApiExRatePaymentService()
    val paymentService = SimpleExRatePaymentService()
    val payment = paymentService.prepare(100, "USD", BigDecimal(50.7))
    println(payment)
}
