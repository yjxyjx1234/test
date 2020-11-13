package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategortDao;
import cn.itcast.travel.dao.impl.CategortDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategortDao categoryDao = new CategortDaoImpl();

    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();

        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs =null;
        if(categorys == null ||categorys.size()==0){
            System.out.println("从数据库里查询");
            cs = categoryDao.findAll();
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }

        }else {
            System.out.println("从redis里查询");
            cs = new ArrayList<Category>();
            for (Tuple tuple :categorys){
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }
        /*return categoryDao.findAll();*/
        return cs;
    }
}
