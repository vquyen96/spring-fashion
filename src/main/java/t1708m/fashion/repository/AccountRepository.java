package t1708m.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1708m.fashion.entity.Account;


public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByEmail (String email);

}
