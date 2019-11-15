package t1708m.fashion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import t1708m.fashion.entity.HelloOrder;
import t1708m.fashion.entity.HelloOrderDetail;
import t1708m.fashion.entity.Product;
import t1708m.fashion.exception.NotEnoughProductsInStockException;
import t1708m.fashion.service.ProductService;
import t1708m.fashion.service.ProductServiceImp;
import t1708m.fashion.service.ShoppingCartService;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class CartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductServiceImp productService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = (ProductServiceImp) productService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        Product product = new Product("abc", BigDecimal.valueOf(30000), 40, "Hello");
        HelloOrderDetail orderDetail = new HelloOrderDetail(2, product);
        shoppingCartService.addOrderDetail(orderDetail);
        System.out.println("Order detail : " + orderDetail.getProduct().getName());
        logger.info("Order detail : " + orderDetail.getProduct().getName());
        System.out.println(shoppingCartService.getTotal());
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("orderDetails", shoppingCartService.getOrderDetailInCart());
        modelAndView.addObject("products", shoppingCartService.getOrderDetailInCart());
        modelAndView.addObject("total", "2");
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @PostMapping("/shoppingCart/checkout")
    public ModelAndView checkout(@ModelAttribute Model model,  @Valid HelloOrder order) {
        try {
            shoppingCartService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }
}
