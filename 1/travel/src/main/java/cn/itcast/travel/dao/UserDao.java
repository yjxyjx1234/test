package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /*根据用户名查询用户信息*/
    public User findByUsername(String username);

    /*用户保存*/
    public void sava(User user);

    User findByUsernameAndPassword(String username, String password);

    User findByCode(String code);

    void updataStatus(User user);
}
