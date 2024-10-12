package com.enotes.service.impl;

import com.enotes.dto.CategoryDto;
import com.enotes.entities.Category;
import com.enotes.repository.CategoryRepository;
import com.enotes.response.CategoryResponse;
import com.enotes.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {

        //---------------------------  Using Mapper -----------------------------
        Category category = mapper.map(categoryDto, Category.class);
        category.setIsDeleted(false);
        category.setCreatedBy(1);
        category.setCreatedOn(new Date());
        Category saveCategory = categoryRepo.save(category);
        if (ObjectUtils.isEmpty(saveCategory)){
            return false;
        }
        else {
            return true;
        }

        //------------------ Without Using Mapper-----------------------------
//        category.setIsDeleted(false);
//        category.setCreatedBy(1);
//        category.setCreatedOn(new Date());
//        Category saveCategory = categoryRepo.save(category);
//        if (ObjectUtils.isEmpty(saveCategory))
//            return false;
//        return true;
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> categories = categoryRepo.findByIsDeletedFalse();
        // Convert into DTO
        List<CategoryDto> categoryDtoList = categories.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();
        return categoryDtoList;
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();

        List<CategoryResponse> categoryList = categories.stream().map(cat -> mapper.map(cat, CategoryResponse.class)).toList();
        return categoryList;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
       Optional<Category> findByCategory =  categoryRepo.findByIdAndIsDeletedFalse(id);

       if (findByCategory.isPresent()){
          Category category =  findByCategory.get();
         return mapper.map(category, CategoryDto.class);
       }
        return null;
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        Optional<Category> findCategoryById = categoryRepo.findById(id);
        if (findCategoryById.isPresent()){
            Category category = findCategoryById.get();
            category.setIsDeleted(true);
            categoryRepo.save(category);
            return true;
        }
        return false;
    }
}
