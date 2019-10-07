package br.com.UploadFIle.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.UploadFIle.Error.ResourceNotFound;
import br.com.UploadFIle.Error.ResourceNotFoundDetails;

@ControllerAdvice
public class ResourceNotFoundHandler {
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity handleResourceNotFoundExecption( ResourceNotFound rnfException) {
		ResourceNotFoundDetails details = ResourceNotFoundDetails.builder()
			.timestamp(new Date().getTime())
			.status(HttpStatus.NOT_FOUND.value())
			.title("File not found")
			.detail(rnfException.getMessage())
			.developerMessage(rnfException.getClass().getName())
			.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
	}

}
