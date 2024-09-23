package hyunec.inflearnhellospring.data

import hyunec.inflearnhellospring.order.Order
import hyunec.inflearnhellospring.order.OrderRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

class JpaOrderRepository : OrderRepository {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun save(order: Order) {
        entityManager.persist(order)
    }
}
