package hyunec.inflearnhellospring.payment

class ObjectFactory {
    fun paymentService(): PaymentService {
        return PaymentService(exRateProvider())
    }

    fun exRateProvider(): ExRateProvider {
        return WebApiExRateProvider()
    }
}
