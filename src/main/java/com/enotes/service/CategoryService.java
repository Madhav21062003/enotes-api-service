package com.enotes.service;

import com.enotes.dto.CategoryDto;
import com.enotes.entities.Category;
import com.enotes.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    public Boolean saveCategory(CategoryDto categoryDto);

    public List<CategoryDto> getAllCategory();
    public List<CategoryResponse> getActiveCategory();

    CategoryDto getCategoryById(Integer id);

    Boolean deleteCategory(Integer id);
}
