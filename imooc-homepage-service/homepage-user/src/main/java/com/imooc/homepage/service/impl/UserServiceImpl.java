package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfoRequest;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.client.CourseClient;
import com.imooc.homepage.dao.HomepageUserCourseDao;
import com.imooc.homepage.dao.HomepageUserDao;
import com.imooc.homepage.entity.HomepageUser;
import com.imooc.homepage.entity.HomepageUserCourse;
import com.imooc.homepage.service.IUserService;
import com.imooc.homepage.vo.CreateUserRequest;
import com.imooc.homepage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final HomepageUserDao homepageUserDao;

    private final HomepageUserCourseDao homepageUserCourseDao;

    /**
     * 业务层在需要时候，调用Feign接口，从而实现调用course服务中的模块方法
     */
    private final CourseClient courseClient;

    @Autowired
    public UserServiceImpl(HomepageUserDao homepageUserDao,HomepageUserCourseDao homepageUserCourseDao,CourseClient courseClient){
        this.homepageUserDao = homepageUserDao;
        this.homepageUserCourseDao = homepageUserCourseDao;
        this.courseClient = courseClient;
    }

    @Override
    public UserInfo createUser(CreateUserRequest request) {
        if (!request.validate()){
            return UserInfo.invalid();
        }
        HomepageUser oldUser = homepageUserDao.findByUsername(request.getUsername());
        if (null != oldUser){
            return UserInfo.invalid();
        }
        //保存用户
        HomepageUser newUser = homepageUserDao.save(new HomepageUser(request.getUsername(),request.getEmail()));

        return new UserInfo(newUser.getId(),newUser.getUsername(),newUser.getEmail());
    }

    @Override
    public UserInfo getUserInfo(Long id) {
        Optional<HomepageUser> user = homepageUserDao.findById(id);
        if (!user.isPresent()){
            return UserInfo.invalid();
        }
        HomepageUser curUser = user.get();
        return new UserInfo(curUser.getId(),curUser.getUsername(),curUser.getEmail());
    }

    @Override
    public UserCourseInfo getUserCourseInfo(Long id) {

        Optional<HomepageUser> user = homepageUserDao.findById(id);
        if (!user.isPresent()){
            return UserCourseInfo.invalid();
        }
        //用户信息 - 实体关系表
        HomepageUser homepageUser = user.get();

        //用户有效信息
        UserInfo userInfo = new UserInfo(homepageUser.getId(),homepageUser.getUsername(),homepageUser.getEmail());

        //用户相对应课程信息
        List<HomepageUserCourse> userCourses = homepageUserCourseDao.findAllByUserId(id);
        if (CollectionUtils.isEmpty(userCourses)){
            return new UserCourseInfo(userInfo, Collections.emptyList());
        }

        //课程对应详细信息  参数ids - 遍历HomepageUserCourse所有id存放与list中  ， 微服务调用
        List<CourseInfo> courseInfos = courseClient.getCourseInfos(
                new CourseInfoRequest(
                        userCourses.stream()
                                .map(HomepageUserCourse::getCourseId).collect(Collectors.toList())));

        //包含用户信息 和 课程信息的VO
        return new UserCourseInfo(userInfo,courseInfos);
    }
}
