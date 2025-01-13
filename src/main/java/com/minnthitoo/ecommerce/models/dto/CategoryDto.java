package com.minnthitoo.ecommerce.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CategoryDto {

    private Long id;

    @NotNull(message = "{required.category.name}")
    @NotBlank(message = "{required.category.name}")
    @Size(min = 3, max = 255, message = "{size.category.name}")
    private String name;

    @NotNull(message = "{required.category.description}")
    @NotBlank(message = "{required.category.description}")
    @Size(min = 3, max = 255, message = "{required.category.description}")
    private String description;

    private Date createdAt;

    private Date updatedAt;

}
