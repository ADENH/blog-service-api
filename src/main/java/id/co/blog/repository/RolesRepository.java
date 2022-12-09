package id.co.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.blog.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer>{

	List<Roles> findByIsDelete(Integer isDelete);
	
	Optional<Roles> findByCode(String code);
	
	Optional<Roles> findById(Integer id);
}
