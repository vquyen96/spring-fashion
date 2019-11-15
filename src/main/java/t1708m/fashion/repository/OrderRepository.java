package t1708m.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1708m.fashion.entity.HelloOrder;

public interface OrderRepository extends JpaRepository<HelloOrder, Integer> {
}
