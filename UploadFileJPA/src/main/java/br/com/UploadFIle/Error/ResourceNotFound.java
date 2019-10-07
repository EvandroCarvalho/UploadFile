package br.com.UploadFIle.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.NotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
	public ResourceNotFound(String message) {
		super(message);
	}
}
