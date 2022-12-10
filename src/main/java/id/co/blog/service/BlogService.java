package id.co.blog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import id.co.blog.dto.BlogDetailResponse;
import id.co.blog.dto.PaginationResponse;
import id.co.blog.dto.PostBlogRequest;
import id.co.blog.dto.ResponseTemplate;
import id.co.blog.model.Blog;

public interface BlogService {

	ResponseEntity<ResponseTemplate> postBlog(PostBlogRequest request);

	ResponseEntity<PaginationResponse<List<Blog>>> displayBlogEntries(String author, String status,int page, int size);

	ResponseEntity<BlogDetailResponse> getDetailBlog(Long id);
}
