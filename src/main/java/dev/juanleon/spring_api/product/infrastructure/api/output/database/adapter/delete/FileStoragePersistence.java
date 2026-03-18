package dev.juanleon.spring_api.product.infrastructure.api.output.database.adapter.delete;

import dev.juanleon.spring_api.common.utils.files.IFileUtils;
import dev.juanleon.spring_api.product.domain.persistence.delete.IFileStoragePersistence;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStoragePersistence implements IFileStoragePersistence {

    private final IFileUtils iFileUtils;

    public FileStoragePersistence(IFileUtils iFileUtils) {
        this.iFileUtils = iFileUtils;
    }

    @Override
    public void deleteFile(String urlFile) {
        this.iFileUtils.deleteFile(urlFile);
    }

    @Override
    public String save(MultipartFile file) {
        return this.iFileUtils.saveFile(file);
    }
}
