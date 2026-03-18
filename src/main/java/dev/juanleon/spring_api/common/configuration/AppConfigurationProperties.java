package dev.juanleon.spring_api.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfigurationProperties {

    private String pathUploadImagesProducts;

    public String getPathUploadImagesProducts() {
        return pathUploadImagesProducts;
    }

    public void setPathUploadImagesProducts(String pathUploadImagesProducts) {
        this.pathUploadImagesProducts = pathUploadImagesProducts;
    }
}
