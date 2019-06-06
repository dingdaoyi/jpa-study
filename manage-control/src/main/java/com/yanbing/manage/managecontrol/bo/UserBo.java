package com.yanbing.manage.managecontrol.bo;

import lombok.Data;
import net.dreamlu.mica.core.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description： TODO
 * <p>
 * Author: dingyw
 * <p>
 * Date: Created in 2019-06-06 11:34
 * <p>
 * Company: xzjc
 * <p>
 * Copyright: Copyright (c) 2019
 * <p>
 * Version: 1.0.0
 * <p>
 * Modified By:
 */
@Data
public class UserBo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    private Long id;
    /**
     * 账号
     */

    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */

    private String name;
    /**
     * 真名
     */

    private String realName;
    /**
     * 邮箱
     */

    private String email;
    /**
     * 手机
     */

    private String phone;
    /**
     * 生日
     */

    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    private LocalDateTime birthday;
    /**
     * 性别
     */

    private Integer sex;

    /**
     * 部门id
     */

    private Long deptId;

    /**
     * 对应role的ids
     */
    private List<Long> roleIds;

}
