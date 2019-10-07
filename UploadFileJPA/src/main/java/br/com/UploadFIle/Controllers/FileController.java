package br.com.UploadFIle.Controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.UploadFIle.DBFileStorageService.DBFileStorageService;
import br.com.UploadFIle.Model.DBFile;
import br.com.UploadFIle.Model.UploadFileResponse;

@RestController
@RequestMapping
public class FileController {

	@Autowired
	private DBFileStorageService dbFileStorageService;

	@PostMapping(path = "/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		DBFile dbFile = dbFileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
				.path(String.valueOf(dbFile.getId())).toUriString();
		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping(path = "/downloadFile/{fileId}")
	public ResponseEntity donwloadFile(@PathVariable long fileId) {
		DBFile dbFile = dbFileStorageService.getFile(fileId);
        return ResponseEntity.ok()        
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + dbFile.getFileName())
                .body(dbFile.getData());
    }

	@PostMapping(path = "/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFIle(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file))
				.collect(Collectors.toList());
	}
}
