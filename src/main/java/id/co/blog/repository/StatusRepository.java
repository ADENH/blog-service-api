package id.co.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.blog.model.StatusBlog;

public interface StatusRepository extends JpaRepository<StatusBlog, String>{

}
