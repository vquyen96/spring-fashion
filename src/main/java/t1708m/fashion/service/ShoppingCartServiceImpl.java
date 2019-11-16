package t1708m.fashion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import t1708m.fashion.entity.Order;
import t1708m.fashion.entity.OrderDetail;
import t1708m.fashion.entity.Product;
import t1708m.fashion.exception.NotEnoughProductsInStockException;
import t1708m.fashion.repository.OrderDetailRepository;
import t1708m.fashion.repository.OrderRepository;
import t1708m.fashion.repository.ProductRepository;

import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ProductRepository productRepository;

    private Map<Long, Integer> productIds = new HashMap<>();

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        if (productIds.containsKey(product.getId())) {
            productIds.replace(product.getId(), productIds.get(product.getId()) + 1);
        } else {
            productIds.put(product.getId(), 1);
        }
    }

    @Override
    public void removeProduct(Product product) {
        Long id = product.getId();
        if (productIds.containsKey(id)) {
            if (productIds.get(id) > 1)
                productIds.replace(id, productIds.get(id) - 1);
            else if (productIds.get(id) == 1) {
                productIds.remove(id);
            }
        }
    }

    /**
     * @return unmodifiable copy of the map
     */
    @Override
    public Map<Long, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(productIds);
    }

    @Override
    public void checkout(Order order) throws NotEnoughProductsInStockException {
        order.setTotalPrice(this.getTotal());
        Set<OrderDetail> orderDetailSet = new HashSet<>();
        for (Map.Entry<Long, Integer> entry : productIds.entrySet()) {
            Optional<Product> productOptional = productRepository.findById((Long) entry.getKey());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();

                if (product.getQuantity() < entry.getValue())
                    throw new NotEnoughProductsInStockException(product);

                product.setQuantity(product.getQuantity() - entry.getValue());
                product = productRepository.save(product);
                OrderDetail orderDetail = new OrderDetail(entry.getValue(), product, order, product.getPrice());
                orderDetailRepository.save(orderDetail);
                orderDetailSet.add(orderDetail);
            }
        }

        order.setTotalPrice(getTotal());
        orderRepository.save(order);
        productRepository.flush();
        productIds.clear();
    }

    @Override
    public double getTotal() throws NotEnoughProductsInStockException {
        int total = 0;
        for(Map.Entry<Long, Integer> entry : productIds.entrySet()) {
            Optional<Product> productOptional = productRepository.findById((Long) entry.getKey());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                if (product.getQuantity() < entry.getValue())
                    throw new NotEnoughProductsInStockException(product);

                total += product.getPrice().intValue() * entry.getValue();
            }
        }
        return Double.valueOf(total);
    }
}
