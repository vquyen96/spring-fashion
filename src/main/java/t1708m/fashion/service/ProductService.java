package t1708m.fashion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import t1708m.fashion.entity.Product;
import t1708m.fashion.repository.ProductRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceImp{

    @Autowired
    ProductRepository productRepository;

    public List<Product> products() {
        //heroRepository.findAll(PageRequest.of(1, 3));
        return productRepository.findActiveProduct(1);
    }

    public Page<Product> products(Specification specification, int page, int limit) {
        return productRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(null);
    }

    public Product create(Product product) {
        product.setStatus(1);
        product.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        product.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return productRepository.save(product);
    }
    public Product update(Product product) {
        product.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        product.setName(product.getName());
        product.setStatus(1);
        return productRepository.save(product);
    }

    public boolean delete(Product product) {
        product.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        product.setStatus(-1);
        productRepository.save(product);
        return true;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Product> findAllProductsPageable(Pageable pageable) {
        return null;
    }
}
