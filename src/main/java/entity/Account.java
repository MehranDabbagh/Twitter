package entity;

import entity.base.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Account extends BaseEntity {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
