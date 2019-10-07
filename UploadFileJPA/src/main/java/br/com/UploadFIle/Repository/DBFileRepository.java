package br.com.UploadFIle.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.UploadFIle.Model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Long>{

}
