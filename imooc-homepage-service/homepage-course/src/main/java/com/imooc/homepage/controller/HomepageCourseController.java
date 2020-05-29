package com.imooc.homepage.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfoRequest;
import com.imooc.homepage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程对外服接口
 *
 * http get post 方法
 */
@Slf4j
@RestController
public class HomepageCourseController {

    private final ICourseService courseService;

    @Autowired
    public HomepageCourseController(ICourseService courseService){
        this.courseService=courseService;
    }

    @GetMapping("/get/course")
    public CourseInfo getCourseInfo(Long id){
        log.info("<homepage-course>:get course -> {}",id);
        return courseService.getCourseInfo(id);
    }

    @PostMapping("/get/courses")
    public List<CourseInfo> getCourseInfoIds(@RequestBody CourseInfoRequest request){
        log.info("<homepage-course>:get courses -> {}", JSON.toJSONString(request));
        return courseService.getCourseInfos(request);
    }
}
