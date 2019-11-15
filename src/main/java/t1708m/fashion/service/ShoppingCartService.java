package t1708m.fashion.service;
import t1708m.fashion.entity.HelloOrder;
import t1708m.fashion.entity.HelloOrderDetail;
import t1708m.fashion.entity.Product;
import t1708m.fashion.exception.NotEnoughProductsInStockException;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Product product);
    void addOrderDetail(HelloOrderDetail orderDetail);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();
    List<HelloOrderDetail> getOrderDetailInCart();

    void checkout() throws NotEnoughProductsInStockException;

    void checkOut(HelloOrder order) throws NotEnoughProductsInStockException;

    double getTotal() throws NotEnoughProductsInStockException;
}
