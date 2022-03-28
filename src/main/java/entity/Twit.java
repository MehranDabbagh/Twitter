package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Twit extends Post{
    private Integer likes;
}
