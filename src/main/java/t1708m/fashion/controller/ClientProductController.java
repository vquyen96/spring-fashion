package t1708m.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import t1708m.fashion.DTO.OrderDTO;
import t1708m.fashion.entity.Order;
import t1708m.fashion.entity.OrderDetail;
import t1708m.fashion.entity.Product;
import t1708m.fashion.exception.NotEnoughProductsInStockException;
import t1708m.fashion.service.ProductService;
import t1708m.fashion.service.ShoppingCartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ClientProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping(method = RequestMethod.GET)
    public String getClient() {
        return "client/index";
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
    @GetMapping("/product-detail")
    public String productDetail() {
        return "client/product-detail";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product product = productService.getById(id);
        if (product == null) {
            return "error/404";
        }
        model.addAttribute("productdetail", product);
        return "client/product-detail";
    }
}
