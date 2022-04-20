package app.buf.buf.service;

import app.buf.buf.dao.FileUpload;
import app.buf.buf.model.File;
import app.buf.buf.util.exception.File.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class FileService {
    private FileUpload fileUpload;

    @Autowired
    public FileService(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    public File storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File dbFile = new File(UUID.randomUUID().toString(), fileName, file.getContentType(), file.getBytes());

            return fileUpload.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public File getFile(String fileId) {
        return fileUpload.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
