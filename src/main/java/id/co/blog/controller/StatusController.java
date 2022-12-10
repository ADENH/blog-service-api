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
import id.co.blog.dto.ResponseTemplate;
import id.co.blog.dto.StatusRequest;
import id.co.blog.model.StatusBlog;
import id.co.blog.service.StatusService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/status")
@SecurityRequirement(name = "bearerAuth")
public class StatusController {
	
	@Autowired
	StatusService statusService;

	@PostMapping
	public ResponseEntity<ResponseTemplate> saveStatus(@RequestBody StatusRequest request) {
		return statusService.saveStatus(request);
	}
	
	@GetMapping
	public ResponseEntity<PaginationResponse<List<StatusBlog>>> saveStatus(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		return statusService.getStatusData(page,size);
	}
}
