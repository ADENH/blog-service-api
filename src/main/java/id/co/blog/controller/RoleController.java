package id.co.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.blog.dto.RoleDto;
import id.co.blog.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@PostMapping
	public ResponseEntity<?> saveRole(@RequestBody RoleDto roleDto){
		return new ResponseEntity<>(roleService.saveRole(roleDto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllRoles(){
		return new ResponseEntity<>(roleService.getAllRoles(),HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable int id){
		return new ResponseEntity<>(roleService.getRoleById(id),HttpStatus.OK);
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<?> getRoleByCode(@PathVariable String code){
		return new ResponseEntity<>(roleService.getRoleByCode(code),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRole(@PathVariable int id,@RequestBody RoleDto roleDto){
		return new ResponseEntity<>(roleService.updateRole(id, roleDto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable int id){
		roleService.deleteRole(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
