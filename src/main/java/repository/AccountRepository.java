package repository;

import entity.Account;

public interface AccountRepository extends GenericRepository<Account,Integer> {
    Account login(String username,String password);
}
