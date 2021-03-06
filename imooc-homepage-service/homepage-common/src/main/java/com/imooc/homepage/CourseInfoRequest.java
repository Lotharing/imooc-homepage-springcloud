package com.imooc.homepage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 课程信息请求对象定义
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfoRequest {

    private List<Long> ids;
}
