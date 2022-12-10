package id.co.blog.configuration;

import java.util.NoSuchElementException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import id.co.blog.dto.ResponseTemplate;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ResponseTemplate(HttpStatus.BAD_REQUEST, error));
	}

	private ResponseEntity<Object> buildResponseEntity(ResponseTemplate apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<Object> handleEntityNotFound(NoSuchElementException ex) {
		ResponseTemplate apiError = new ResponseTemplate(HttpStatus.NOT_FOUND, ex.getMessage());
		return buildResponseEntity(apiError);
	}

}
