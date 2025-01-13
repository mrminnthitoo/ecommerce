package com.minnthitoo.ecommerce.controllers.api.admin;

import com.minnthitoo.ecommerce.controllers.api.errorcodes.ErrorCodes;
import com.minnthitoo.ecommerce.controllers.api.response.ApiResponse;
import com.minnthitoo.ecommerce.models.dto.CategoryDto;
import com.minnthitoo.ecommerce.models.dto.RestResponse;
import com.minnthitoo.ecommerce.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ApiResponse apiResponse;

    @GetMapping()
    public ResponseEntity<RestResponse> getAllCategories(){
        List<CategoryDto> categories = this.categoryService.getAllCategories();
        return this.apiResponse.successResponse(
                HttpStatus.OK,
                "all categories",
                categories
        );
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<RestResponse> getCategoryById(@PathVariable Long categoryId){
        Optional<CategoryDto> result = this.categoryService.getCategoryById(categoryId);
        if (result.isEmpty()){
            return this.apiResponse.errorResponse(
                    HttpStatus.NOT_FOUND,
                    "not found",
                    ErrorCodes.NOT_FOUND,
                    new Object()
            );
        }else {
            return this.apiResponse.successResponse(
                    HttpStatus.OK,
                    "found",
                    result.get()
            );
        }
    }

    @PostMapping
    public ResponseEntity<RestResponse> createCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errors = this.apiResponse.getFieldErrors(bindingResult.getFieldErrors());
            return this.apiResponse.errorResponse(
                    HttpStatus.BAD_REQUEST,
                    "validation fail",
                    ErrorCodes.VALIDATION_ERROR,
                    errors
            );
        }else {
            CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
            return this.apiResponse.successResponse(
                    HttpStatus.CREATED,
                    "created",
                    createdCategory
            );
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<RestResponse> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return this.apiResponse.errorResponse(
                    HttpStatus.NOT_FOUND,
                    "not found",
                    ErrorCodes.NOT_FOUND,
                    this.apiResponse.getFieldErrors(bindingResult.getFieldErrors())
            );
        }else {
            Optional<CategoryDto> result = this.categoryService.getCategoryById(categoryId);
            if (result.isEmpty()){
                return this.apiResponse.errorResponse(
                        HttpStatus.NOT_FOUND,
                        "not found",
                        ErrorCodes.NOT_FOUND,
                        new Object()
                );
            }else {
                categoryDto.setId(categoryId);
                CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto);
                return this.apiResponse.successResponse(
                        HttpStatus.OK,
                        "updated",
                        updatedCategory
                );
            }
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<RestResponse> deleteCategoryById(@PathVariable Long categoryId){
        Optional<CategoryDto> result = this.categoryService.getCategoryById(categoryId);
        if (result.isEmpty()){
            return this.apiResponse.errorResponse(
                    HttpStatus.NOT_FOUND,
                    "not found",
                    ErrorCodes.NOT_FOUND,
                    new Object()
            );
        }else {
            CategoryDto deletedCategory = this.categoryService.deleteCategoryById(categoryId);
            return this.apiResponse.successResponse(
                    HttpStatus.OK,
                    "deleted",
                    deletedCategory
            );
        }
    }

}
