package mm.com.mtp.utils;

import mm.com.mtp.exception.FileStorageException;
import mm.com.mtp.exception.ResourceNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Nay Myo Htet on 10/31/2019.
 */
public class UploadFileUtil {

    public static Path fileStorageLocation;

    public static void createUploadFile(MultipartFile file, String fileName) {
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public static Resource getResource(String fileName, String fileStoragePath) {
        try {
            fileStorageLocation = Paths.get(fileStoragePath).toAbsolutePath().normalize();
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;

            } else {
                throw new ResourceNotFoundException("File ", "file name", fileName);
            }
        } catch (MalformedURLException ex) {
            throw new ResourceNotFoundException("File not found ", fileName, ex);
        }
    }

    public static void createUploadFolderPath(String fileStoragePath) {
        fileStorageLocation = Paths.get(fileStoragePath)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
}
