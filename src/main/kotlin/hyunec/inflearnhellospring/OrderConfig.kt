package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.data.JdbcOrderRepository
import hyunec.inflearnhellospring.order.OrderRepository
import hyunec.inflearnhellospring.order.OrderService
import hyunec.inflearnhellospring.order.OrderServiceImpl
import hyunec.inflearnhellospring.order.OrderServiceTxProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@Import(DataConfig::class)
class OrderConfig {
    @Bean
    fun orderRepository(dataSource: DataSource): OrderRepository {
        return JdbcOrderRepository(dataSource)
    }

    @Bean
    fun orderService(
        platformTransactionManager: PlatformTransactionManager,
        orderRepository: JdbcOrderRepository
    ): OrderService {
        return OrderServiceTxProxy(
            OrderServiceImpl(orderRepository),
            platformTransactionManager
        )
    }
}
