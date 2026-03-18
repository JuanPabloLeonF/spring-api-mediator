package dev.juanleon.spring_api.product.domain.persistence.delete;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStoragePersistence {
    public void deleteFile(String urlFile);
    public String save(MultipartFile file);
}
