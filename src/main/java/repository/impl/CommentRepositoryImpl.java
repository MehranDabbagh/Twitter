package repository.impl;

import entity.Comment;
import repository.CommentRepository;

public class CommentRepositoryImpl extends GenericRepositoryImpl<Comment,Integer> implements CommentRepository {

    public CommentRepositoryImpl(Class<Comment> commentClass) {
        super(commentClass);
    }
}
