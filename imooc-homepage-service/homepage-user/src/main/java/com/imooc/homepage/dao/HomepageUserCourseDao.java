package com.imooc.homepage.dao;

import com.imooc.homepage.entity.HomepageUserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户课程接口访问
 */
public interface HomepageUserCourseDao extends JpaRepository<HomepageUserCourse,Long> {

    /**
     * 根据用户ID查询所拥有的课程信息
     * @param userId
     * @return
     */
    List<HomepageUserCourse> findAllByUserId(Long userId);
}
