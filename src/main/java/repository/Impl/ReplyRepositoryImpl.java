package repository.Impl;

import entity.Reply;
import repository.ReplyRepository;

import java.util.List;

public class ReplyRepositoryImpl  extends GenericRepositoryImpl<Reply,Integer> implements ReplyRepository {
    public ReplyRepositoryImpl(Class<Reply> replyClass) {
        super(replyClass);
    }
}
