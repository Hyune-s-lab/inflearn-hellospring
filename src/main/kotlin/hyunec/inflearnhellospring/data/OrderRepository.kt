package hyunec.inflearnhellospring.data

import hyunec.inflearnhellospring.order.Order
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

class OrderRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    fun save(order: Order) {
        entityManager.persist(order)
    }
}
