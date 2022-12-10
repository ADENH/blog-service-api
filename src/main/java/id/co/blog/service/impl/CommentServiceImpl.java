package id.co.blog.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.co.blog.configuration.Constant;
import id.co.blog.dto.PaginationResponse;
import id.co.blog.dto.PostCommentRequest;
import id.co.blog.dto.ResponseTemplate;
import id.co.blog.model.Blog;
import id.co.blog.model.Comment;
import id.co.blog.model.Users;
import id.co.blog.repository.BlogRepository;
import id.co.blog.repository.CommentRepository;
import id.co.blog.repository.UsersRepository;
import id.co.blog.service.CommentService;

/**
 * @author adenurhidayat.com
 * Dec 10, 2022
 * 10:28:17 AM
 */
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	public ResponseEntity<ResponseTemplate> postComment(PostCommentRequest request) {
		
		Blog blog = blogRepository.findById(request.getBlogId()).orElseThrow(() -> new NoSuchElementException(Constant.BLOG_TIDAK_DITEMUKAN));
		
		Users user = usersRepository.findByUsername(request.getAuthor()).orElseThrow(() -> new NoSuchElementException(Constant.USER_TIDAK_DITEMUKAN));
		Comment comment = new Comment();
		comment.setBlog(blog);
		comment.setContent(request.getContent());
		comment.setUsers(user);
		comment.setUrl(request.getUrl());
		
		commentRepository.save(comment);
		
		return new ResponseEntity<>(new ResponseTemplate(HttpStatus.OK, "Success post comment"),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<PaginationResponse<List<Comment>>> commentByBlog(Long blogId, int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		
		Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new NoSuchElementException(Constant.BLOG_TIDAK_DITEMUKAN));
		
		Page<Comment> commentData = commentRepository.findAllByBlog(blog, paging);
		
		PaginationResponse<List<Comment>> response = new PaginationResponse<>();
		response.setPage(commentData.getNumber());
		response.setSize(commentData.getSize());
		response.setTotal(commentData.getTotalPages());
		response.setData(commentData.getContent());
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
}
