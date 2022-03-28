package entity;

import entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Account extends BaseEntity {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    @ManyToMany
    private Set<Account> follower;
    @ManyToMany
    private Set<Account> following;
}
