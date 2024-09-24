package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.data.JdbcOrderRepository
import hyunec.inflearnhellospring.order.OrderRepository
import hyunec.inflearnhellospring.order.OrderService
import hyunec.inflearnhellospring.order.OrderServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@Import(DataConfig::class)
@EnableTransactionManagement
class OrderConfig {
    @Bean
    fun orderRepository(dataSource: DataSource): OrderRepository {
        return JdbcOrderRepository(dataSource)
    }

    @Bean
    fun orderService(orderRepository: JdbcOrderRepository): OrderService {
        return OrderServiceImpl(orderRepository)
    }
}
