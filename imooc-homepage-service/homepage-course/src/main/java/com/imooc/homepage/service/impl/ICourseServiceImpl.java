package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfoRequest;
import com.imooc.homepage.dao.HomepageCourseDao;
import com.imooc.homepage.entity.HomepageCourse;
import com.imooc.homepage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 课程服务功能实现
 */
@Slf4j
@Service
public class ICourseServiceImpl implements ICourseService {

    @Autowired
    private HomepageCourseDao homepageCourseDao;

    @Override
    public CourseInfo getCourseInfo(Long id) {
        Optional<HomepageCourse> course = homepageCourseDao.findById(id);
        //orElse 存在则返回 不存在就返回(对象 无效课程)
        return buildCourseInfo(course.orElse(HomepageCourse.invaild()));
    }

    @Override
    public List<CourseInfo> getCourseInfos(CourseInfoRequest request) {

        if (CollectionUtils.isEmpty(request.getIds())){
            return Collections.emptyList();
        }

        List<HomepageCourse> courses = homepageCourseDao.findAllById(request.getIds());

        //返回对应的List
        return courses.stream()
                .map(this::buildCourseInfo)
                .collect(Collectors.toList());
    }

    /**
     * 根据数据记录构造对象信息
     * @param course
     * @return
     */
    private CourseInfo buildCourseInfo(HomepageCourse course){
        return CourseInfo.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseType(course.getCourseType() == 0 ? "免费课程" : "实战课程")
                .courseIcon(course.getCourseIcon())
                .courseIntro(course.getCourseIntro())
                .build();
    }

}
