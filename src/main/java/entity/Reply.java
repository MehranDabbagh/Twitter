package entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Reply extends Post {
    @ManyToOne
    private Comment comment;
    @ManyToOne
    private Account account;
}
