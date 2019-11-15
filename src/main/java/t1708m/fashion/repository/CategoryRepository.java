package t1708m.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import t1708m.fashion.entity.ProductCategory;

import java.util.List;


public interface CategoryRepository extends JpaRepository<ProductCategory, Integer>, JpaSpecificationExecutor<ProductCategory> {

    @Query("select h from ProductCategory as h where h.status = :status")
    List<ProductCategory> findActiveCategory(@Param("status") int status);

    List<ProductCategory> findAllByStatus(int status);
}
