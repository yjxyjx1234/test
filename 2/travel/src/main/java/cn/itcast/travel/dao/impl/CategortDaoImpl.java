package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategortDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategortDaoImpl implements CategortDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));

    }
}
