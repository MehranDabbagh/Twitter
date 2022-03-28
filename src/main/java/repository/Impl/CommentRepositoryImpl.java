package repository.Impl;

import entity.Comment;
import repository.CommentRepository;

import java.util.List;

public class CommentRepositoryImpl extends GenericRepositoryImpl<Comment,Integer> implements CommentRepository {

    public CommentRepositoryImpl(Class<Comment> commentClass) {
        super(commentClass);
    }
}
