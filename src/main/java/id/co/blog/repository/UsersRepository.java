package id.co.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.blog.model.Users;

public interface UsersRepository extends JpaRepository<Users, String>{

	Optional<Users> findByUsername(String username);
}
