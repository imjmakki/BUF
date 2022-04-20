package app.buf.buf.dao;

import app.buf.buf.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUpload extends JpaRepository<File, String> {
}
