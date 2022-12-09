package id.co.blog.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.co.blog.dto.UsersDto;
import id.co.blog.model.Roles;
import id.co.blog.model.Users;
import id.co.blog.repository.RolesRepository;
import id.co.blog.repository.UsersRepository;
import id.co.blog.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	RolesRepository rolesRepository;
	
	@Override
	public Users getUsersByUsername(String username) {
		return usersRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Data Tidak Ditemukan"));
	}

	@Override
	public Users saveDataUser(UsersDto usersDto) {
		
		Roles role = rolesRepository.findByCode(usersDto.getRole()).orElseThrow(() -> new NoSuchElementException("Data Tidak Ditemukan"));
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		String password = passwordEncoder.encode(usersDto.getPassword());
		usersDto.setPassword(password);
		
		Users user = new Users();
		user.setEmail(usersDto.getEmail());
		user.setFullName(usersDto.getFullname());
		user.setPassword(password);
		user.setUsername(usersDto.getUsername());
		user.setRole(role);
		
		String username = Optional.of(usersRepository.save(user)).map(v -> v.getUsername()).orElseThrow(() -> new NoSuchElementException("Data Tidak Disimpan"));
		return usersRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Data Tidak Ditemukan"));
	}
}
