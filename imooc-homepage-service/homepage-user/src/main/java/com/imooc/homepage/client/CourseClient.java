package com.imooc.homepage.client;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 通过Feign访问课程微服务
 *
 * @FeignClient() 指定注册在Eureka中的服务名
 *
 * HTTP 调用方式
 *
 * Feign 可以结合 hystrix 做服务熔断 (访问这接口时候，如果发生错误，可以实现一些兜底的策略【定义，熔断降级 fallback【调用失败返回兜底信息】】)
 */

@FeignClient(value = "eureka-client-homepage-course",fallback = CourseClientHystrix.class)
public interface CourseClient {

    /**
     * Feign接口
     * 调用course服务中的接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/homepage-course/get/course",method = RequestMethod.GET)
    CourseInfo getCourseInfo(Long id);

    /**
     * Feign接口
     * 调用course微服务中暴漏的接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/homepage-course/get/courses",method = RequestMethod.POST)
    List<CourseInfo> getCourseInfos(@RequestBody CourseInfoRequest request);

}
