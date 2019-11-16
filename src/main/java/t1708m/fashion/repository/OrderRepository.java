package t1708m.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1708m.fashion.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
