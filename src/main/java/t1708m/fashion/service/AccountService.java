package t1708m.fashion.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import t1708m.fashion.DTO.AccountDTO;
import t1708m.fashion.entity.Account;


public interface AccountService extends UserDetailsService {
    Account findByEmail(String email);

    Account save(AccountDTO register);
}
