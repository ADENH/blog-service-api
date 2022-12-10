package id.co.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.blog.dto.BlogDetailResponse;
import id.co.blog.dto.PaginationResponse;
import id.co.blog.dto.PostBlogRequest;
import id.co.blog.dto.ResponseTemplate;
import id.co.blog.model.Blog;
import id.co.blog.service.BlogService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * @author adenurhidayat.com 
 * Dec 10, 2022 
 * 12:32:11 AM
 */
@RestController
@RequestMapping("/api/data")
@SecurityRequirement(name = "bearerAuth")
public class BlogController {

	@Autowired
	BlogService blogService;

	@PostMapping
	public ResponseEntity<ResponseTemplate> postBlog(@RequestBody PostBlogRequest request) {
		return blogService.postBlog(request);
	}

	@GetMapping("/entries")
	public ResponseEntity<PaginationResponse<List<Blog>>> displayBlogEntries(@RequestParam String author,@RequestParam String status,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		return blogService.displayBlogEntries(author,status,page,size);
	}
	
	@GetMapping
	public ResponseEntity<BlogDetailResponse> DetailBlog(@RequestParam Long id) {
		return blogService.getDetailBlog(id);
	}
	
	@PostMapping("/update")
	public ResponseEntity<ResponseTemplate> updateBlog(@RequestParam Long id,@RequestBody PostBlogRequest request) {
		return blogService.updateBlog(id,request);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<ResponseTemplate> updateBlog(@RequestParam Long id) {
		return blogService.deleteBlog(id);
	}
}
