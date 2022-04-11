package service.impl;

import Connnection.SessionFactorySingleton;
import entity.Account;
import lombok.var;
import org.hibernate.SessionFactory;
import repository.impl.AccountRepositoryImpl;
import service.AccountService;

import java.util.List;
import java.util.Objects;

public class AccountServiceImpl implements AccountService {
    private AccountRepositoryImpl accountRepository;
    protected SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    public AccountServiceImpl() {
        accountRepository =new AccountRepositoryImpl(Account.class);
    }
    @Override
    public Integer login(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
               Account account1=accountRepository.login(account.getUserName(),account.getPassword());
               transaction.commit();
               if(account1!=null){
                   return account1.getId();
               }

            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
        return 0;
    }

    @Override
    public Account findByUsername(String username) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                List<Account> accounts = accountRepository.findAll();
                transaction.commit();
                Account account2=accounts
                        .stream()
                        .filter(account1 -> Objects.equals(account1.getUserName(),username))
                        .findFirst()
                        .get();
                if(account2!=null){
                    return account2;
                }
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
        return null;
    }

    @Override
    public Integer create(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                Integer id= accountRepository.save(account).getId();
                transaction.commit();
                return id;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public Account findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                Account account = accountRepository.findById(id);
                transaction.commit();
                return account;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Account> findAll() {

        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                List<Account> accounts = accountRepository.findAll();
                transaction.commit();
                return accounts;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void Update(Account account) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                accountRepository.update(account);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void Delete(Integer id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                accountRepository.deleteById(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
