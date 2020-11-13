package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {


    User findByCode(String code);

    User findByUsername(String username);

    void save(User user);

    void updataStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
