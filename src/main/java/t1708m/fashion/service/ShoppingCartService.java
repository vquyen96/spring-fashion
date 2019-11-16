package t1708m.fashion.service;
import t1708m.fashion.entity.Order;
import t1708m.fashion.entity.OrderDetail;
import t1708m.fashion.entity.Product;
import t1708m.fashion.exception.NotEnoughProductsInStockException;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Product product);
    void removeProduct(Product product);

    Map<Long, Integer> getProductsInCart();

    void checkout(Order order) throws NotEnoughProductsInStockException;

    double getTotal() throws NotEnoughProductsInStockException;
}
