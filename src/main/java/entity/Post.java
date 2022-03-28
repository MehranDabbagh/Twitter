package entity;

import entity.base.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Post extends BaseEntity {
private String content;
}
