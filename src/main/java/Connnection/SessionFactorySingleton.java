package Connnection;

import entity.*;
import entity.base.BaseEntity;
import lombok.var;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure() // goes and fetches configuration from hibernate.cfg.xml
                    .build();
            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Comment.class)
                    .addAnnotatedClass(BaseEntity.class)
                    .addAnnotatedClass(Post.class)
                    .addAnnotatedClass(Twit.class)
                    .addAnnotatedClass(Reply.class)
                    .addAnnotatedClass(Account.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;

    }
}
