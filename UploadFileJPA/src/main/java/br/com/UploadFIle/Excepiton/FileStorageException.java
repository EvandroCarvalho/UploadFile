package br.com.UploadFIle.Excepiton;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class FileStorageException extends RuntimeException {
	
	public FileStorageException(String message ) {
		super(message);
	}

}
