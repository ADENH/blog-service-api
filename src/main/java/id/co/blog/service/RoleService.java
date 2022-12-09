package id.co.blog.service;

import java.util.List;

import id.co.blog.dto.RoleDto;

public interface RoleService {

	List<RoleDto> getAllRoles();
	RoleDto getRoleByCode(String code);
	RoleDto getRoleById(int id);
	RoleDto saveRole(RoleDto roleDto);
	RoleDto updateRole(int id,RoleDto roleDto);
	void deleteRole(int id);

}
