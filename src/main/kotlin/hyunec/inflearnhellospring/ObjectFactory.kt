package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.exrate.CachedExRateProvider
import hyunec.inflearnhellospring.exrate.WebApiExRateProvider
import hyunec.inflearnhellospring.payment.ExRateProvider
import hyunec.inflearnhellospring.payment.PaymentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectFactory {
    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(cachedExRateProvider())
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
        return WebApiExRateProvider()
    }

    @Bean
    fun cachedExRateProvider(): CachedExRateProvider {
        return CachedExRateProvider(exRateProvider())
    }
}
