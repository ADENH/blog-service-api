package id.co.blog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import id.co.blog.dto.PaginationResponse;
import id.co.blog.dto.ResponseTemplate;
import id.co.blog.dto.StatusRequest;
import id.co.blog.model.StatusBlog;

public interface StatusService {

	ResponseEntity<ResponseTemplate> saveStatus(StatusRequest request);

	ResponseEntity<PaginationResponse<List<StatusBlog>>> getStatusData(int page, int size);

}
