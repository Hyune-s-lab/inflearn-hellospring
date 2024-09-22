package hyunec.inflearnhellospring.data

import hyunec.inflearnhellospring.order.Order
import jakarta.persistence.EntityManagerFactory

class OrderRepository(
        private val emf: EntityManagerFactory
) {
    fun save(order: Order) {
        val em = emf.createEntityManager()
        val transaction = em.transaction

        transaction.begin()

        try {
            em.persist(order)
            transaction.commit()
        } catch (e: Exception) {
            if (transaction.isActive) transaction.rollback()
            throw e
        } finally {
            if (em.isOpen) em.close()
        }
    }
}
