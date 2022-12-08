package id.co.blog.service;

import id.co.blog.dto.UsersDto;
import id.co.blog.model.Users;

public interface UsersService {

	Users getUsersByUsername(String username);
	Users saveDataUser(UsersDto accountDto);
}
