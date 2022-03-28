package repository.Impl;

import entity.Twit;
import repository.TwitRepository;

import java.util.List;

public class TwitRepositoryImpl extends GenericRepositoryImpl<Twit,Integer> implements TwitRepository {
    public TwitRepositoryImpl(Class<Twit> twitClass) {
        super(twitClass);
    }
}
