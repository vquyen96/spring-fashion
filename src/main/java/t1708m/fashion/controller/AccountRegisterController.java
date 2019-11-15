package t1708m.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import t1708m.fashion.DTO.AccountDTO;
import t1708m.fashion.entity.Account;
import t1708m.fashion.service.AccountService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class AccountRegisterController {

    @Autowired
    AccountService accountService;

    @ModelAttribute("account")
    public AccountDTO accountDTO() {
        return new AccountDTO();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("account") @Valid AccountDTO accountDTO,
                                      BindingResult result){

        Account existing = accountService.findByEmail(accountDTO.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "register";
        }

        accountService.save(accountDTO);
        return "redirect:/register?success";
    }

}
