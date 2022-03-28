package repository.impl;

import entity.Account;
import repository.AccountRepository;

public class AccountRepositoryImpl extends GenericRepositoryImpl<Account,Integer> implements AccountRepository{

    public AccountRepositoryImpl(Class<Account> accountClass) {
        super(accountClass);
    }
}
