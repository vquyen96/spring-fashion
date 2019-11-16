package t1708m.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import t1708m.fashion.DTO.OrderDTO;
import t1708m.fashion.entity.Account;
import t1708m.fashion.entity.Order;
import t1708m.fashion.entity.OrderDetail;
import t1708m.fashion.entity.Product;
import t1708m.fashion.exception.NotEnoughProductsInStockException;
import t1708m.fashion.repository.AccountRepository;
import t1708m.fashion.repository.ProductRepository;
import t1708m.fashion.service.ProductService;
import t1708m.fashion.service.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/")
public class ClientController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Product> products = productService.products();
        model.addAttribute("products", products);
        return "/client/home";
    }

    @GetMapping("/product")
    public String product(Model model) {
        List<Product> products = productService.products();
        model.addAttribute("products", products);

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


    @GetMapping("/add-product")
    public String addProduct(HttpServletRequest request) {
        productService.create(new Product("Esprit Ruffle Shirt", "customer/images/product-01.jpg", BigDecimal.valueOf(1664), 40, "hello"));
        productService.create(new Product("Herschel supply", "customer/images/product-02.jpg", BigDecimal.valueOf(3531), 40, "hello"));
        productService.create(new Product("Only Check Trouser", "customer/images/product-03.jpg", BigDecimal.valueOf(2550), 40, "hello"));
        productService.create(new Product("Classic Trench Coat", "customer/images/product-04.jpg", BigDecimal.valueOf(7500), 40, "hello"));
        productService.create(new Product("Front Pocket Jumper", "customer/images/product-05.jpg", BigDecimal.valueOf(4375), 40, "hello"));
        productService.create(new Product("Vintage Inspired Classic", "customer/images/product-06.jpg", BigDecimal.valueOf(9320), 40, "hello"));
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @PostMapping("/add-to-cart/{id}")
    public String addToCart(Model model, HttpServletRequest request, @PathVariable("id") Long id) throws NotEnoughProductsInStockException {
        Product product = productService.getById(id);
        productService.create(product);
        shoppingCartService.addProduct(product);

        //Chuyển về trang cũ
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model) throws NotEnoughProductsInStockException {
        Map<Long, Integer> productIds = shoppingCartService.getProductsInCart();
        Map<Product, Integer> products = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : productIds.entrySet()) {
            Optional<Product> productOptional = productRepository.findById((Long) entry.getKey());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                products.put(product, entry.getValue());
            }
        }

        model.addAttribute("products", products);
        model.addAttribute("total", shoppingCartService.getTotal());
        model.addAttribute("order", new OrderDTO());
        return "client/shopping-cart";
    }

    @PostMapping("/checkout")
    public String checkOut(Model model, HttpServletRequest request) throws NotEnoughProductsInStockException {
        Account account = accountRepository.save(new Account("quyen96", "vquyenaaa@gmail.com", 1));
        Order order = new Order("Quyen", "HaNoi", account);
        shoppingCartService.checkout(order);
        model.addAttribute("products", shoppingCartService.getProductsInCart());
        model.addAttribute("total", shoppingCartService.getTotal());
        model.addAttribute("order", new Order());

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

}
