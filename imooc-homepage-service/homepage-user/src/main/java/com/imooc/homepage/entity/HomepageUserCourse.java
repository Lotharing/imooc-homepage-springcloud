package com.imooc.homepage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * homepage_user_course 对应实体类定义
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "homepage_user_course")
public class HomepageUserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Basic
    @Column(name = "course_id",nullable = false)
    private Long courseId;

    @Basic
    @Column(name = "create_time",nullable = false)
    @CreatedDate
    private Date createTime;

    @Basic
    @Column(name = "update_time",nullable = false)
    @LastModifiedDate
    private Date updateTime;

}
