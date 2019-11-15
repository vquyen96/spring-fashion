package t1708m.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import t1708m.fashion.DTO.OrderDTO;
import t1708m.fashion.entity.Order;
import t1708m.fashion.entity.OrderDetail;
import t1708m.fashion.entity.Product;
import t1708m.fashion.service.ProductService;
import t1708m.fashion.service.ShoppingCartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ClientController {
    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Product> products = productService.products();
        model.addAttribute("products", products);
        return "/client/home";
    }

    @GetMapping("/product")
    public String product() {
        return "client/product";
    }
    @GetMapping("/contact")
    public String contact() {
        return "client/contact";
    }
    @GetMapping("/about")
    public String about() {
        return "client/about";
    }
    @GetMapping("/blog")
    public String blog() {
        return "client/blog";
    }
    @GetMapping("/blog-detail")
    public String blogDetail() {
        return "client/blog-detail";
    }
    @GetMapping("/home-03")
    public String home3() {
        return "client/home-03";
    }
    @GetMapping("/home-02")
    public String home2() {
        return "client/home-02";
    }

    @GetMapping("/shoping-cart")
    public String shoppingCart() {
        return "shopping-cart";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/add-to-cart")
    public String addToCart(Model model) {
        Product product = new Product("Converse All Star", BigDecimal.valueOf(340000), 40, "hello");
//        HelloOrderDetail orderDetail = new HelloOrderDetail(1, product1);
//        shoppingCartService.addOrderDetail(orderDetail);
        shoppingCartService.addProduct(product);
        model.addAttribute("orderDetails", new ArrayList<OrderDetail>());
        model.addAttribute("products", shoppingCartService.getProductsInCart());
        model.addAttribute("total", shoppingCartService.getTotal());
        model.addAttribute("order", new Order());

        return "client/shopping-cart";
    }

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model) {
        List<OrderDetail> orderDetails = shoppingCartService.getOrderDetailInCart();
        System.out.println(orderDetails.size());
        System.out.println(shoppingCartService.getOrderDetailInCart());
        System.out.println(shoppingCartService.getTotal());
        System.out.println(shoppingCartService.getTotal());
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("products", shoppingCartService.getOrderDetailInCart());
        model.addAttribute("total", shoppingCartService.getTotal());
        model.addAttribute("order", new OrderDTO());
        return "client/shopping-cart";
    }

    @GetMapping("/checkout")
    public String checkOut(Model model) {
        Order order = new Order();
        shoppingCartService.checkOut(order);
        List<OrderDetail> orderDetails = shoppingCartService.getOrderDetailInCart();
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("products", shoppingCartService.getOrderDetailInCart());
        model.addAttribute("total", shoppingCartService.getTotal());
        model.addAttribute("order", new Order());

        return "client/shopping-cart";
    }

}
