package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    /*根据类别分页查询*/
    public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String rname);

    /*根据id查询详情*/
    Route findOne(String rid);
}
