package app.buf.buf.dao;

import app.buf.buf.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUpload extends JpaRepository<File, Long> {
}
