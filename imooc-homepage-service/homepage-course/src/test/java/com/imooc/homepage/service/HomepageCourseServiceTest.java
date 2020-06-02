package com.imooc.homepage.service;


import com.imooc.homepage.Application;
import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.dao.HomepageCourseDao;
import com.imooc.homepage.entity.HomepageCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HomepageCourseServiceTest {

    @Autowired
    private HomepageCourseDao courseDao;

    @Autowired
    private ICourseService courseService;

    @Test
    public void testCreateCourseInfo(){
        HomepageCourse course1 = new HomepageCourse("JDK11特性解读",0,"www.imooc.com","解读新版本特性");
        HomepageCourse course2 = new HomepageCourse("SpringCloud",1,"www.imooc.com","微服务学习");

        System.out.println(courseDao.saveAll(Arrays.asList(course1,course2)
        ).size());

    }




}
