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

    private Map<Product, Integer> products = new HashMap<>();
    private Map<Long, Integer> productIds = new HashMap<>();

    private List<OrderDetail> orderDetailMap = new ArrayList<>();

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * If product is in the map just increment quantity by 1.
     * If product is not in the map with, add it with quantity 1
     *
     * @param product
     */
    @Override
    public void addProduct(Product product) {
        if (productIds.containsKey(product.getId())) {
            System.out.println(product.getName());
            productIds.replace(product.getId(), productIds.get(product.getId()) + 1);
        } else {
            System.out.println(product.getName()+"--"+product.getId());
            productIds.put(product.getId(), 1);
        }
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        if (orderDetailMap.contains(orderDetail)) {
            int index = orderDetailMap.indexOf(orderDetail);
            orderDetail.setQuantity(orderDetail.getQuantity() + orderDetailMap.get(index).getQuantity());
            orderDetailMap.set(index, orderDetail);
        } else {
            orderDetailMap.add(orderDetail);
            System.out.println(orderDetailMap.size());
            System.out.println(orderDetail.getProduct().getName());
        }
        System.out.println(orderDetail.getProduct().getName());
    }

    /**
     * If product is in the map with quantity > 1, just decrement quantity by 1.
     * If product is in the map with quantity 1, remove it from map
     *
     * @param product
     */
    @Override
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
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

    /**
     * @return unmodifiable copy of the map
     */
    @Override
    public List<OrderDetail> getOrderDetailInCart() {
        return orderDetailMap;
    }

    @Override
    public void checkout() throws NotEnoughProductsInStockException {

    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     *
     * @throws t1708m.fashion.exception.NotEnoughProductsInStockException
     */
    @Override
    public void checkout(Order order) throws NotEnoughProductsInStockException {
        order.setTotalPrice(this.getTotal());
        Set<OrderDetail> orderDetailSet = new HashSet<>(orderDetailMap);
        for (Map.Entry<Long, Integer> entry : productIds.entrySet()) {
            // Refresh quantity for every product before checking
            System.out.println(entry.getKey());
            Optional<Product> productOptional = productRepository.findById((Long) entry.getKey());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                System.out.println(product.getName());
                if (product.getQuantity() < entry.getValue())
                    throw new NotEnoughProductsInStockException(product);
                product.setQuantity(product.getQuantity() - entry.getValue());
                productRepository.save(product);
                OrderDetail orderDetail = new OrderDetail(entry.getValue(), product, product.getPrice());
                orderDetail.setOrder(order);
                orderDetailRepository.save(orderDetail);
                orderDetailSet.add(orderDetail);
            }
        }
//        order.setOrderDetails(orderDetailSet);
//        System.out.println(order.getOrderDetails().size());
        order.setTotalPrice(getTotal());
        orderRepository.save(order);
        productRepository.flush();
        productIds.clear();
    }

    @Override
    public void checkOut(Order order) throws NotEnoughProductsInStockException {
        Set<OrderDetail> orderDetailSet = new HashSet<>(orderDetailMap);
        order.setOrderDetails(orderDetailSet);
        order.setTotalPrice(this.getTotal());
        orderRepository.save(order);
        for (OrderDetail orderDetail : orderDetailMap) {
            orderDetail.setOrder(order);
            System.out.println(orderDetail.getId());
            orderDetailRepository.save(orderDetail);
        }

        orderDetailMap.clear();
    }


    @Override
    public double getTotal() throws NotEnoughProductsInStockException {
//        return 0;
//        int total = 0;
//        for (HelloOrderDetail orderDetail : orderDetailMap) {
//                total += orderDetail.getProduct().getPrice().intValue() * orderDetail.getQuantity();
//        }

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
