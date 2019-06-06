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
package com.yanbing.manage.managecontrol.controller;


import com.yanbing.manage.managecontrol.bo.UserBo;
import com.yanbing.manage.managecontrol.entity.User;
import com.yanbing.manage.managecontrol.service.IUserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 控制器
 *
 * @author Chill
 */
@ApiIgnore
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

	private final IUserService userService;

	/**
	 * 查询单条
	 */
	@ApiOperation(value = "查看详情", notes = "传入id", position = 1)
	@GetMapping("/detail/{id}")
	public Mono<R<User>> detail(@PathVariable Long id) {
		return Mono.just(userService.selectUser(id));
	}

	@GetMapping
	public Mono<List<User>> listUsers() {
		return Mono.just(userService.selectUsers());
	}

	@PostMapping("{deptId}")
	public Mono<Long> save(@RequestBody User user,@PathVariable("deptId") Long deptId){

		return userService.save(user,deptId);
	}

	@PutMapping
	public Mono<Long> updateUser(@RequestBody UserBo user){

		return userService.updateUser(user);
	}


	@DeleteMapping("{id}")
	public Mono<R> deleteUser(@PathVariable Long id){

		return userService.deleteUser(id);
	}
}
