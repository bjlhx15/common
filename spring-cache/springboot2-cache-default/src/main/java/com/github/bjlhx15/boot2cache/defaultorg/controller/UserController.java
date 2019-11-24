package com.github.bjlhx15.boot2cache.defaultorg.controller;

import com.github.bjlhx15.boot2cache.defaultorg.entity.User;
import com.github.bjlhx15.boot2cache.defaultorg.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
