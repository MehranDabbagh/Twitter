package repository.Impl;

import entity.Account;
import repository.AccountRepository;

import java.util.List;

public class AccountRepositoryImpl extends GenericRepositoryImpl<Account,Integer> implements AccountRepository{

    public AccountRepositoryImpl(Class<Account> accountClass) {
        super(accountClass);
    }
}
