package repository.impl;

import entity.Twit;
import repository.TwitRepository;

public class TwitRepositoryImpl extends GenericRepositoryImpl<Twit,Integer> implements TwitRepository {
    public TwitRepositoryImpl(Class<Twit> twitClass) {
        super(twitClass);
    }
}
