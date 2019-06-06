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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
@Table(name = "t_role")
@Entity
@ApiModel(value = "Role对象", description = "Role对象")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler","userList"})
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "主键")

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	/**
	 * 租户编号
	 */
	@ApiModelProperty(value = "租户编号")
	@Column(name="tenant_code", length=60)
	private String tenantCode;

	/**
	 * 父主键
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "父主键")
	@Column(name="parent_id")
	private Long parentId;

	/**
	 * 角色名
	 */
	@ApiModelProperty(value = "角色名")
	@Column(name="role_name")
	private String roleName;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	@Column(name="sort")
	private Integer sort;

	/**
	 * 角色别名
	 */
	@ApiModelProperty(value = "角色别名")
	@Column(name="role_alias")
	private String roleAlias;

	/**
	 * 是否已删除
	 */

	@ApiModelProperty(value = "是否已删除")
	@Column(name="is_deleted")
	private Integer isDeleted;
	@ManyToMany(mappedBy = "roleList")
	@JsonIgnoreProperties(value = {"roleList"})
	private List<User> userList;


}
