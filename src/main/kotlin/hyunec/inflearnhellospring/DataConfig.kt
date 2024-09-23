package hyunec.inflearnhellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class DataConfig {
    @Bean
    fun dataSource(): DataSource {
        return EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build()
    }

//    @Bean
//    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
//        return LocalContainerEntityManagerFactoryBean().apply {
//            dataSource = dataSource()
//            setPackagesToScan("hyunec.inflearnhellospring")
//            jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
//                setDatabase(Database.H2)
//                setGenerateDdl(true)
//                setShowSql(true)
//            }
//        }
//    }
//
//    @Bean
//    fun persistenceAnnotationBeanPostProcessor(): BeanPostProcessor {
//        return PersistenceAnnotationBeanPostProcessor()
//    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource())
    }
}
