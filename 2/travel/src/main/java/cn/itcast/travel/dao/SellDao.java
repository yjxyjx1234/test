package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellDao {
    Seller findById(int sid);
}
