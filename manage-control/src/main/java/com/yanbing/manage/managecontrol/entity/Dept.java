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
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "t_dept")
@Entity
@ApiModel(value = "Dept对象", description = "Dept对象")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler","userList"})
public class Dept implements Serializable {

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
	 * 部门名
	 */
	@ApiModelProperty(value = "部门名")
	@Column(name="dept_name")
	private String deptName;

	/**
	 * 部门全称
	 */
	@ApiModelProperty(value = "部门全称")
	@Column(name="full_name")
	private String fullName;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	@Column(name="sort")
	private Integer sort;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	@Column(name="remark")
	private String remark;

	/**
	 * 是否已删除
	 */
	@Column(name="is_deleted")
	@ApiModelProperty(value = "是否已删除")
	private Integer isDeleted;

	@OneToMany(mappedBy = "dept",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	//级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
	//拥有mappedBy注解的实体类为关系被维护端
	private List<User> userList;


}
