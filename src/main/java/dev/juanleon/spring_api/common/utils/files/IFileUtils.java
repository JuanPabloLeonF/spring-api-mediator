package dev.juanleon.spring_api.common.utils.files;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public interface IFileUtils {

    public void deleteFile(String urlImage);
    public String saveFile(MultipartFile file);

    default public String generateUniqueFileName(String fileName) {
        return UUID.randomUUID() + "_" + fileName;
    }

    default public Path getUploadPath(String urlPath) {
        return Path.of(urlPath);
    }

    default public void createDirectoriesIfNotExists(Path path) throws Exception {
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
}
