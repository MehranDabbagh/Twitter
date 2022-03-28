package entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Twit extends Post{
    @ManyToOne
    private Account account;
    private Integer likes;
}
