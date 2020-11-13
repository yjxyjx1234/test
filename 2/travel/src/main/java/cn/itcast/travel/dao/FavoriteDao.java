package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {
    Favorite findByRidAndUid(int anInt, int parseInt);

    void add(int parseInt, int uid);

    int findCountByRid(int rid);
}
