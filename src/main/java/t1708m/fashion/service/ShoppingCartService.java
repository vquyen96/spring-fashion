package t1708m.fashion.service;
import t1708m.fashion.entity.Order;
import t1708m.fashion.entity.OrderDetail;
import t1708m.fashion.entity.Product;
import t1708m.fashion.exception.NotEnoughProductsInStockException;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Product product);
    void addOrderDetail(OrderDetail orderDetail);

    void removeProduct(Product product);

    Map<Long, Integer> getProductsInCart();
    List<OrderDetail> getOrderDetailInCart();

    void checkout() throws NotEnoughProductsInStockException;

    void checkout(Order order) throws NotEnoughProductsInStockException;

    void checkOut(Order order) throws NotEnoughProductsInStockException;

    double getTotal() throws NotEnoughProductsInStockException;
}
