package id.co.blog.service.impl;

import java.util.ArrayList;
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
		
		Users user = usersRepository.findByUsername(author).orElseThrow(() -> new NoSuchElementException("Users Tidak Ditemukan"));
		
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

}
