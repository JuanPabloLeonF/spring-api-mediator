package dev.juanleon.spring_api.product.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class RequestProduct {

    @NotBlank
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotNull
    @Min(value = 100, message = "Price must be greater than or equal to 100.0")
    private Double price;

    @NotNull
    private MultipartFile image;

    public RequestProduct() {
    }

    public RequestProduct(String name, Double price, MultipartFile image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
