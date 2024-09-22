package hyunec.inflearnhellospring

import hyunec.inflearnhellospring.data.OrderRepository
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
class DataConfig {
    @Bean
    fun dataSource(): DataSource {
        return EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build()
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        return LocalContainerEntityManagerFactoryBean().apply {
            dataSource = dataSource()
            setPackagesToScan("hyunec.inflearnhellospring")
            jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
                setDatabase(Database.H2)
                setGenerateDdl(true)
                setShowSql(true)
            }
        }
    }

    @Bean
    fun persistenceAnnotationBeanPostProcessor(): BeanPostProcessor {
        return PersistenceAnnotationBeanPostProcessor()
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory): JpaTransactionManager {
        return JpaTransactionManager(emf)
    }

    @Bean
    fun orderRepository(): OrderRepository {
        return OrderRepository()
    }
}
