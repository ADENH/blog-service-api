package id.co.blog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import id.co.blog.dto.PaginationResponse;
import id.co.blog.dto.PostCommentRequest;
import id.co.blog.dto.ResponseTemplate;
import id.co.blog.model.Comment;

public interface CommentService {

	ResponseEntity<ResponseTemplate> postComment(PostCommentRequest request);

	ResponseEntity<PaginationResponse<List<Comment>>> commentByBlog(Long blogId, int page, int size);

}
