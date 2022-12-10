package id.co.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.co.blog.model.Blog;
import id.co.blog.model.Users;

public interface BlogRepository extends JpaRepository<Blog, Long>{
	
	Page<Blog> findBlogByStatusAndUsers(String Status,Users user,Pageable page);

}
