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

import id.co.blog.dto.PaginationResponse;
import id.co.blog.dto.PostCommentRequest;
import id.co.blog.dto.ResponseTemplate;
import id.co.blog.model.Comment;
import id.co.blog.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping
	public ResponseEntity<ResponseTemplate> postComment(@RequestBody PostCommentRequest request) {
		return commentService.postComment(request);
	}
	
	@GetMapping("/all-comment")
	public ResponseEntity<PaginationResponse<List<Comment>>> displayBlogEntries(@RequestParam Long blogId,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		return commentService.commentByBlog(blogId,page,size);
	}
}
