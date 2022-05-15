package mm.com.mtp.service;

import mm.com.mtp.exception.FileStorageException;
import mm.com.mtp.utils.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileStorageService {

    private Path fileStorageLocation;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    @Autowired
    FileStorageProperties fileStorageProperties;

    public void changeDirectory(String dirName) {
        fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + "/" + dirName).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
                    ex);
        }
    }

    public String storeFile(String dirName, MultipartFile file) {

        // Normalize file name
        String fileName = StringUtils.cleanPath(getAbsoluteName(file.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            changeDirectory(dirName);
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String dirName, String fileName) {
        try {
            changeDirectory(dirName);
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileStorageException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileStorageException("File not found " + fileName, ex);
        }
    }

    public String getAbsoluteName(String fileName) {
        String timeStamp = LocalDateTime.now().format(dateTimeFormatter);
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return fileName;
        } else {
           /* return fileName.substring(0, index).replaceAll("\\s", "_")
                    + "_" + timeStamp
                    + fileName.substring(index);*/
            return timeStamp + fileName.substring(index);
        }
    }
}
