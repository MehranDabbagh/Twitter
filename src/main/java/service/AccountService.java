package service;

import entity.Account;
import service.base.BaseService;

public interface AccountService extends BaseService<Account,Integer> {
    Integer login(Account account);
}
