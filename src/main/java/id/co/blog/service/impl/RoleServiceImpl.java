package id.co.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.hibernate.UnresolvableObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.blog.dto.RoleDto;
import id.co.blog.model.Roles;
import id.co.blog.repository.RolesRepository;
import id.co.blog.service.RoleService;

/**
 * @author adenurhidayat.com
 * Dec 9, 2022
 * 10:52:06 PM
 */
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RolesRepository rolesRepository;

	@Override
	public List<RoleDto> getAllRoles() {
		List<Roles> allPositions = rolesRepository.findByIsDelete(0);
		return new ArrayList<>(allPositions.stream()
				.map(position -> new RoleDto(position.getCode(), position.getName())).collect(Collectors.toList()));
	}

	@Override
	public RoleDto getRoleByCode(String code) {
		Roles position = rolesRepository.findByCode(code).orElseThrow(() -> new NoSuchElementException("Role Tidak Ditemukan"));
		return new RoleDto(position);
	}

	@Override
	public RoleDto getRoleById(int id) {
		Roles position = rolesRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Role Tidak Ditemukan"));	
		return new RoleDto(position);
	}

	@Override
	public RoleDto saveRole(RoleDto roleDto) {
		Roles role = new Roles(roleDto);
		role = rolesRepository.save(role);
		if (role.getId() == -1)
			throw new UnresolvableObjectException(Roles.class, "Gagal Save Database");
		return roleDto;
	} 	

	@Override
	public RoleDto updateRole(int id, RoleDto roleDto) {
		Roles role = rolesRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Role Tidak Ditemukan"));
		role.setCode(roleDto.getCode());
		role.setName(roleDto.getName());
		rolesRepository.save(role);
		return new RoleDto(role);
	}

	@Override
	public void deleteRole(int id) {
		Roles role = rolesRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Role Tidak Ditemukan"));
		role.setIsDelete(1);
		rolesRepository.save(role);
		
	}

}
