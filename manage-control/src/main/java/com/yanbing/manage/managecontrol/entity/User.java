/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package com.yanbing.manage.managecontrol.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.dreamlu.mica.core.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
@Entity
@Table(name="t_user")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
public class User{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */

	@ApiModelProperty(value = "主键")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 账号
	 */
	@Column(name="account", length=60)
	private String account;
	/**
	 * 密码
	 */
	@Column(name="password")
	private String password;
	/**
	 * 昵称
	 */
	@Column(name="name")
	private String name;
	/**
	 * 真名
	 */
	@Column(name="real_name")
	private String realName;
	/**
	 * 邮箱
	 */
	@Column(name="email")
	private String email;
	/**
	 * 手机
	 */
	@Column(name="phone")
	private String phone;
	/**
	 * 生日
	 */
	@Column(name="birthday")
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	private LocalDateTime birthday;
	/**
	 * 性别
	 */
	@Column(name="sex")
	private Integer sex;

	/**
	 * 部门信息
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "dept_id")
	@JsonIgnoreProperties(value = {"userList"})
	private Dept dept;


	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "t_user_role",
			joinColumns = @JoinColumn(name = "u_id",referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name = "r_id",referencedColumnName="id"))
	@JsonIgnoreProperties(value = {"userList"})
	private List<Role> roleList;

}
