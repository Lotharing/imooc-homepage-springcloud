package com.imooc.homepage.vo;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * 用户课程信息对象定义
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseInfo {

    //用户信息
    private UserInfo userInfo;

    //课程信息
    private List<CourseInfo> courseInfos;

    public static UserCourseInfo invalid(){
        return new UserCourseInfo(UserInfo.invalid(), Collections.emptyList());
    }
}
