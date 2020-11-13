package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
        /*String sql = "select count(*) from tab_route where cid =?";*/
        String sql = "select count(*) from tab_route where 1 = 1 ";
        /*为了方便拼接*/
        StringBuilder sb = new StringBuilder(sql);

        /*加条件*/
        ArrayList params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }

        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");
            /*模糊查询  这才是真的传入的*/
            params.add("%" + rname + "%");
        }

        sql = sb.toString();

        /*转数组*/
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        /*String sql = "select * from tab_route where cid =? limit ? , ?";*/
        String sql = "select * from tab_route where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        /*加条件*/
        ArrayList params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }

        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");
            /*模糊查询  这才是真的传入的*/
            params.add("%" + rname + "%");
        }
        sb.append(" limit ? , ? ");
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);


        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }
}
