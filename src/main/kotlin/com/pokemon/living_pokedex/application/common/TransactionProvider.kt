package com.pokemon.living_pokedex.application.common

import com.pokemon.living_pokedex.domain.common.Transaction
import java.util.logging.Logger
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.support.TransactionTemplate

@Component
class TransactionProvider(
    private val platformTransactionManager: PlatformTransactionManager
) : Transaction {

    companion object {
        private val log = Logger.getLogger(this::class.java.name)
    }

    @Throws(Throwable::class)
    override fun <T> execute(action: () -> T): T {
        val transactionTemplate = TransactionTemplate(platformTransactionManager)
        transactionTemplate.propagationBehavior =
            TransactionDefinition.PROPAGATION_REQUIRED
        return try {
            transactionTemplate.execute { action() }!!
        } catch (e: Throwable) {
            log.severe("Erro ao executar a transação, realizando rollback [${e.message}]")
            throw e
        }
    }
}