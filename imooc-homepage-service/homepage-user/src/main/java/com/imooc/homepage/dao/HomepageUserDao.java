package com.imooc.homepage.dao;

import com.imooc.homepage.entity.HomepageUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户接口访问
 */
public interface HomepageUserDao extends JpaRepository<HomepageUser,Long> {
    /**
     * 通过用户名查找记录
     * @param username
     * @return
     */
    HomepageUser findByUsername(String username);
}
