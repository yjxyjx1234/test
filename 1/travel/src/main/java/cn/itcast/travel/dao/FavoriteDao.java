package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public interface FavoriteDao {
    /*根据uid 和 rid查询*/
    Favorite findByRidAndUid(int rid, int uid);

    /*根据rid查询收藏次数*/

    int findCountByRid(int rid);

    void add(int parseInt, int uid);
}
