package com.imooc.homepage.client;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfoRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 熔断降级策略
 */
@Component
public class CourseClientHystrix implements CourseClient{

    @Override
    public CourseInfo getCourseInfo(Long id) {
        return CourseInfo.invaild();
    }

    @Override
    public List<CourseInfo> getCourseInfos(CourseInfoRequest request) {
        return Collections.emptyList();
    }
}
