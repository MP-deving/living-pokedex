package com.pokemon.living_pokedex.application.config

import java.util.Properties
import javax.sql.DataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement(order = Ordered.HIGHEST_PRECEDENCE + 1)
class TransactionConfig {

    @Bean
    fun transactionManager(routingDataSource: DataSource): PlatformTransactionManager =
        JpaTransactionManager().apply {
            this.entityManagerFactory = entityManagerFactory(routingDataSource).`object`
        }

    @Bean
    fun entityManagerFactory(dataSource: DataSource) =
        LocalContainerEntityManagerFactoryBean().apply {
            this.dataSource = dataSource
            this.setPackagesToScan("com.pokemon.living_pokedex")
            this.jpaVendorAdapter = HibernateJpaVendorAdapter()
            setJpaProperties(
                Properties().apply {
                    setProperty("hibernate.hbm2ddl.auto", "validate")
                    setProperty(
                        "hibernate.phisical_naming_strategy",
                        "org.springframework.boot.model.naming.CamelCaseToUnderscoresNamingStrategy"
                    )
                }
            )
            this.afterPropertiesSet()
        }
}