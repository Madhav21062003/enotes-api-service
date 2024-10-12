package com.enotes.service;

import com.enotes.entities.Category;

import java.util.List;

public interface CategoryService {

    public Boolean saveCategory(Category category);

    public List<Category> getAllCategory();

}
