package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.payment.ExRateProvider
import hyunec.inflearnhellospring.payment.ExRateProviderStub
import hyunec.inflearnhellospring.payment.PaymentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal

@Configuration
class TestObjectFactory {
    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(exRateProvider())
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
        return ExRateProviderStub(BigDecimal.valueOf(1000))
    }
}
