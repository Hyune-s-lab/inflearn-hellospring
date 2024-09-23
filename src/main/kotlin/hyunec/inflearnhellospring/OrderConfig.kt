package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.data.JpaOrderRepository
import hyunec.inflearnhellospring.order.OrderRepository
import hyunec.inflearnhellospring.order.OrderService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.orm.jpa.JpaTransactionManager

@Configuration
@Import(DataConfig::class)
class OrderConfig {
    @Bean
    fun orderRepository(): OrderRepository {
        return JpaOrderRepository()
    }

    @Bean
    fun orderService(jpaTransactionManager: JpaTransactionManager): OrderService {
        return OrderService(orderRepository(), jpaTransactionManager)
    }
}
