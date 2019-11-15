package t1708m.fashion.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import t1708m.fashion.entity.OrderDetail;

public interface OrderDetailRepository  extends JpaRepository<OrderDetail, Integer> {
}
