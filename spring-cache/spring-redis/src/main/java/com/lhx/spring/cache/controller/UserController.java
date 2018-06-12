package com.lhx.spring.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhx.spring.cache.entity.User;
import com.lhx.spring.cache.services.IUserService;

@RestController
public class UserController {
	@Autowired
	private IUserService iUserService;

	@GetMapping("/user")
	@ResponseBody
	public User queryUser() {
		return this.iUserService.queryUser("1");
	}

	@GetMapping("/user/u")
	@ResponseBody
	public String updateUser() {
		this.iUserService.updateUser("1");
		return "ok";
	}

	@GetMapping("/user/d")
	@ResponseBody
	public String deleteUser() {
		this.iUserService.deleteUser("1");
		return "ok";
	}
}
