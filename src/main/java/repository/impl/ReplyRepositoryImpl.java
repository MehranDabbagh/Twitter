package repository.impl;

import entity.Reply;
import repository.ReplyRepository;

public class ReplyRepositoryImpl  extends GenericRepositoryImpl<Reply,Integer> implements ReplyRepository {
    public ReplyRepositoryImpl(Class<Reply> replyClass) {
        super(replyClass);
    }
}
