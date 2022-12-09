package id.co.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.blog.dto.UsersDto;
import id.co.blog.model.Users;
import id.co.blog.service.UsersService;

@RestController
@RequestMapping("/accounts")
public class UsersController {

	@Autowired
	UsersService userService;
	
	@GetMapping("/{username}")
	public Users getEmployeeByUSername(@RequestParam String username) {
		return userService.getUsersByUsername(username);
	}
	
	@PostMapping
	public Users saveDataUsers(@RequestBody UsersDto accountDto) {
		return userService.saveDataUser(accountDto);
	}
}
