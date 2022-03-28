package entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends Post {
    @ManyToOne
    private Account account;
    @ManyToOne
    private Twit twit;
}
