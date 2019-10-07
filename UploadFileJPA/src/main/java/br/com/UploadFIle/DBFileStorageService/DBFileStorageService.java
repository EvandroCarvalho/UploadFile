package br.com.UploadFIle.DBFileStorageService;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.UploadFIle.Error.ResourceNotFound;
import br.com.UploadFIle.Excepiton.FileStorageException;
import br.com.UploadFIle.Excepiton.MyFileNotFoundException;
import br.com.UploadFIle.Model.DBFile;
import br.com.UploadFIle.Repository.DBFileRepository;
import lombok.extern.slf4j.Slf4j;

@Service
public class DBFileStorageService {
	
	@Autowired
    static Logger logger = Logger.getLogger(DBFileStorageService.class);
	
	@Autowired
	private DBFileRepository dbFileRepository;

	public DBFile storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence" + fileName);
			}
			DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
			logger.info("Save file: " + dbFile.toString() );
			return dbFileRepository.save(dbFile);
		} catch (IOException e) {
			throw new FileStorageException("Could not store file " + fileName + "Please try again!");
		}
	}
	
	public DBFile getFile(long fileId) {
		logger.info("Search file in data base: " + fileId);
		return dbFileRepository.findById(fileId).orElseThrow(() -> new ResourceNotFound("File not found with id " + fileId));
	}
}
