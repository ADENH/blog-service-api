package id.co.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.co.blog.model.Blog;
import id.co.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	
	Page<Comment> findAllByBlog(Blog blog,Pageable pageable);
}
