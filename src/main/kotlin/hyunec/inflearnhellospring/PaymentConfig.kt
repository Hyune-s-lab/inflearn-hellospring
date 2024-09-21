package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.api.ApiTemplate
import hyunec.inflearnhellospring.api.ErApiExtractor
import hyunec.inflearnhellospring.api.HttpClientApiExecutor
import hyunec.inflearnhellospring.exrate.CachedExRateProvider
import hyunec.inflearnhellospring.exrate.RestTemplateExRateProvider
import hyunec.inflearnhellospring.payment.ExRateProvider
import hyunec.inflearnhellospring.payment.PaymentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.JdkClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Clock

@Configuration
class PaymentConfig {
    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(cachedExRateProvider(), clock())
    }

    @Bean
    fun apiTemplate(): ApiTemplate {
        return ApiTemplate(HttpClientApiExecutor(), ErApiExtractor())
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate(JdkClientHttpRequestFactory())
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
//        return WebApiExRateProvider(apiTemplate())
        return RestTemplateExRateProvider(restTemplate())
    }

    @Bean
    fun cachedExRateProvider(): CachedExRateProvider {
        return CachedExRateProvider(exRateProvider())
    }

    @Bean
    fun clock(): Clock {
        return Clock.systemDefaultZone()
    }
}
