package com.imooc.homepage.dao;

import com.imooc.homepage.entity.HomepageCourse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 交给jpa实现 <映射实体,主键类型>
 */
public interface HomepageCourseDao extends JpaRepository<HomepageCourse,Long> {


}
