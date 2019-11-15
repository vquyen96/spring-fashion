package t1708m.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import t1708m.fashion.entity.Account;
import t1708m.fashion.repository.AccountRepository;
import t1708m.fashion.repository.ProductRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(method = RequestMethod.GET)
    public String getAdmin() {
        return "/admin/index";
    }

    @GetMapping(value = "/list-product")
    public String product() {
        return "/admin/product/index";
    }

    @GetMapping(value = "/create-product")
    public String Creproduct() {
        return "/admin/product/form";
    }

    @GetMapping("/account-edit")
    public String edit() {
        return "/admin/account/edit";
    }

    @GetMapping("/account-list")
    public String list() {
        return "/admin/account/list";
    }

    @GetMapping("/account-create")
    public String create() {
        return "/admin/account/create";
    }
}
