package com.imooc.homepage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseInfo {

    private Long id;

    private String courseName;

    private String courseType;

    private String courseIcon;

    private String courseIntro;

    /**
     * 工具方法 / ID无效时候 返回没有意义的CourseInfo 不返回错误
     * @return
     */
    public static CourseInfo invaild(){
        return new CourseInfo(-1L,"","","","");
    }

}
