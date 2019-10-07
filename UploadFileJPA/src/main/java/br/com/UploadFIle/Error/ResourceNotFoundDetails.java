package br.com.UploadFIle.Error;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResourceNotFoundDetails {
	private String title;
	private int status;
	private String detail;
	private long timestamp;
	private String developerMessage;
	
	private ResourceNotFoundDetails() {
		
	}
}
