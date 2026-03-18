package dev.juanleon.spring_api.common.utils.files;

import dev.juanleon.spring_api.common.configuration.AppConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.*;
import java.util.Objects;

@Component
public class FileUtils implements IFileUtils {

    private final AppConfigurationProperties appConfigurationProperties;

    public FileUtils(AppConfigurationProperties appConfigurationProperties) {
        this.appConfigurationProperties = appConfigurationProperties;
    }

    public void deleteFile(String urlImage) {

        try {

            if (urlImage == null || urlImage.isBlank()) {
                throw new RuntimeException("No existe ningun archivo en la ruta: " + urlImage);
            }

            Path filePath = this.getUploadPath(this.appConfigurationProperties.getPathUploadImagesProducts())
                    .resolve(urlImage)
                    .normalize();

            Files.deleteIfExists(filePath);

        } catch (Exception exception) {
            throw new RuntimeException("Error eliminando archivo: " + exception.getMessage());
        }
    }

    public String saveFile(MultipartFile file) {

        String uniqueFileName;

        try (InputStream inputStream = file.getInputStream()) {

            String fileName = StringUtils.cleanPath(
                    Objects.requireNonNull(file.getOriginalFilename())
            );

            uniqueFileName = generateUniqueFileName(fileName);

            Path uploadPath = this.getUploadPath(this.appConfigurationProperties.getPathUploadImagesProducts());

            createDirectoriesIfNotExists(uploadPath);

            Files.copy(
                    inputStream,
                    uploadPath.resolve(uniqueFileName),
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (Exception exception) {
            throw new RuntimeException("Error guardando archivo: " + exception.getMessage());
        }

        return uniqueFileName;
    }
}