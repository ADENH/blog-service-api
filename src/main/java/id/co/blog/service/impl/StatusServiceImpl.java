package id.co.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.co.blog.dto.PaginationResponse;
import id.co.blog.dto.ResponseTemplate;
import id.co.blog.dto.StatusRequest;
import id.co.blog.model.StatusBlog;
import id.co.blog.repository.StatusRepository;
import id.co.blog.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	StatusRepository statusRepository;
	
	@Override
	public ResponseEntity<ResponseTemplate> saveStatus(StatusRequest request) {
		StatusBlog statusBlog = new StatusBlog();
		statusBlog.setCode(request.getCode());
		statusBlog.setDescription(request.getDescription());
		
		statusBlog = statusRepository.save(statusBlog);
		
		return new ResponseEntity<>(new ResponseTemplate(HttpStatus.OK, "Success save status"),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<PaginationResponse<List<StatusBlog>>> getStatusData(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		
		Page<StatusBlog> statusBlog = statusRepository.findAll(paging);
		
		PaginationResponse<List<StatusBlog>> response = new PaginationResponse<>();
		response.setPage(statusBlog.getNumber());
		response.setSize(statusBlog.getSize());
		response.setTotal(statusBlog.getTotalPages());
		response.setData(statusBlog.getContent());
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
