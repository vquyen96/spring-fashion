package t1708m.fashion.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import t1708m.fashion.entity.Product;

import java.util.Optional;

public interface ProductServiceImp {

    Optional<Product> findById(Long id);

    Page<Product> findAllProductsPageable(Pageable pageable);

}
