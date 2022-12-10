package id.co.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.blog.dto.PaginationResponse;
import id.co.blog.dto.PostBlogRequest;
import id.co.blog.dto.ResponseTemplate;
import id.co.blog.model.Blog;
import id.co.blog.service.BlogService;

/**
 * @author adenurhidayat.com 
 * Dec 10, 2022 
 * 12:32:11 AM
 */
@RestController
@RequestMapping("/api/post")
public class BlogController {

	@Autowired
	BlogService blogService;

	@PostMapping
	public ResponseEntity<ResponseTemplate> postBlog(@RequestBody PostBlogRequest request) {
		return blogService.postBlog(request);
	}

	@PostMapping("/blog-entries")
	public ResponseEntity<PaginationResponse<List<Blog>>> displayBlogEntries(@RequestParam String author,@RequestParam String status,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		return blogService.displayBlogEntries(author,status,page,size);
	}
}
