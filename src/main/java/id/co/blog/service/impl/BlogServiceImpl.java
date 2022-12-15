package id.co.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.UnresolvableObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.co.blog.configuration.Constant;
import id.co.blog.dto.BlogDetailResponse;
import id.co.blog.dto.PaginationResponse;
import id.co.blog.dto.PostBlogRequest;
import id.co.blog.dto.ResponseTemplate;
import id.co.blog.model.Blog;
import id.co.blog.model.Roles;
import id.co.blog.model.Users;
import id.co.blog.repository.BlogRepository;
import id.co.blog.repository.UsersRepository;
import id.co.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Override
	public ResponseEntity<ResponseTemplate> postBlog(PostBlogRequest request) {
		
		Users user =usersRepository.findByUsername(request.getAuthorId()).orElseThrow(() -> new NoSuchElementException("Users Tidak Ditemukan")); 
		
		Blog blog = new Blog();
		blog.setContent(request.getContent());
		blog.setTags(request.getTags());
		blog.setStatus(request.getStatus()); // status draft / published
		blog.setTitle(request.getTitle());
		blog.setUsers(user);
		blog.setViewed(0L);
		
		blog = blogRepository.save(blog);
		
		if (blog.getId() == -1)
			throw new UnresolvableObjectException(Roles.class, "Gagal Save Database");
		
		return new ResponseEntity<>(new ResponseTemplate(HttpStatus.OK, "Success save blog content"),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<PaginationResponse<List<Blog>>> displayBlogEntries(String author, String status,int page, int size) {
		
		List<Blog> blogs = new ArrayList<Blog>();
		Pageable paging = PageRequest.of(page, size);
		
		Users user = usersRepository.findByUsername(author).orElseThrow(() -> new NoSuchElementException(Constant.USER_TIDAK_DITEMUKAN));
		
		Page<Blog> blogData = blogRepository.findBlogByStatusAndUsers(status, user, paging);
		
		for(Blog blog:blogData.getContent()) {
			String content = StringUtils.abbreviate(blog.getContent(),Constant.JUMLAH_HURUF_DITAMPILKAN);
			blog.setContent(content);
			blogs.add(blog);
		}
		
		PaginationResponse<List<Blog>> response = new PaginationResponse<>();
		response.setPage(blogData.getNumber());
		response.setSize(blogData.getSize());
		response.setTotal(blogData.getTotalPages());
		response.setData(blogs);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BlogDetailResponse> getDetailBlog(Long id) {
		BlogDetailResponse response = new BlogDetailResponse();
		Blog blog = blogRepository.findById(id).orElseThrow(() -> new NoSuchElementException(Constant.BLOG_TIDAK_DITEMUKAN));
		
		response.setAuthor(blog.getUsers().getUsername());
		response.setContent(blog.getContent());
		response.setPublishDate(blog.getCreateDate());
		response.setTitle(blog.getTitle());
		response.setViewed(blog.getViewed());
		
		blog.setViewed(blog.getViewed()+1L);
		blogRepository.save(blog);
		return new ResponseEntity<BlogDetailResponse>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseTemplate> updateBlog(Long id, PostBlogRequest request) {
		Blog blog = blogRepository.findById(id).orElseThrow(() -> new NoSuchElementException(Constant.BLOG_TIDAK_DITEMUKAN));
		blog.setTitle(request.getTitle());
		blog.setContent(request.getContent());
		blog.setUpdateDate(new Date());
		blog.setTags(request.getTags());
		blog.setStatus(request.getStatus());
		blog = blogRepository.save(blog);
		return new ResponseEntity<>(new ResponseTemplate(HttpStatus.OK, "Success update blog content"),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseTemplate> deleteBlog(Long id) {
		
		Blog blog = blogRepository.findById(id).orElseThrow(() -> new NoSuchElementException(Constant.BLOG_TIDAK_DITEMUKAN));
		blog.setStatus("deleted");
		blog = blogRepository.save(blog);
		return new ResponseEntity<>(new ResponseTemplate(HttpStatus.OK, "Success delete blog content"),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PaginationResponse<List<Blog>>> displayAllBlogEntries(String status, int page, int size) {
		List<Blog> blogs = new ArrayList<Blog>();
		Pageable paging = PageRequest.of(page, size);
		
		Page<Blog> blogData = blogRepository.findAllBlogByStatus(status, paging);
		
		for(Blog blog:blogData.getContent()) {
			String content = StringUtils.abbreviate(blog.getContent(),Constant.JUMLAH_HURUF_DITAMPILKAN);
			blog.setContent(content);
			blogs.add(blog);
		}
		
		PaginationResponse<List<Blog>> response = new PaginationResponse<>();
		response.setPage(blogData.getNumber());
		response.setSize(blogData.getSize());
		response.setTotal(blogData.getTotalPages());
		response.setData(blogs);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
