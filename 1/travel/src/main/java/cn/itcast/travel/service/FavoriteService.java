package cn.itcast.travel.service;

public interface FavoriteService {
    /*判断是否收藏 uid为0 不是有效吗  不sql自增从1开始*/
    public boolean isFavorite(String rid, int uid);


    void add(String rid, int uid);
}
