package repository.impl;

import entity.Account;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.AccountRepository;

public class AccountRepositoryImpl extends GenericRepositoryImpl<Account,Integer> implements AccountRepository{

    public AccountRepositoryImpl(Class<Account> accountClass) {
        super(accountClass);
    }

    @Override
    public Account login(String username, String password) {
        Session session= sessionFactory.getCurrentSession();
        String hql = "FROM Account E WHERE E.userName = :username and E.password= :password";
        Query query = session.createQuery(hql);
        query.setParameter("username",username);
        query.setParameter("password",password);
       Account account = ((Account) query.getSingleResult());
        return  account;
    }
}
