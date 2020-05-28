package com.imooc.homepage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基本用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Long id;

    private String username;

    private String email;

    /**
     * 工具方法 / ID无效时候 返回没有意义的UserInfo 不返回错误
     * @return
     */
    public static UserInfo invalid(){
        return new UserInfo(-1L,"","");
    }
}
