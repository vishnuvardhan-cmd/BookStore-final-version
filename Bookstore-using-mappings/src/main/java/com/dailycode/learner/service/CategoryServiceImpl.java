package com.dailycode.learner.service;

import com.dailycode.learner.dto.mapper;
import com.dailycode.learner.dto.requestdto.CategoryRequestDto;
import com.dailycode.learner.dto.responsedto.CategoryResponseDto;
import com.dailycode.learner.entity.Category;
import com.dailycode.learner.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                ()->new IllegalArgumentException("could not find category with id: "+categoryId)
        );
    }

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        Category category=new Category();
        category.setName(categoryRequestDto.getName());
        Category save = categoryRepository.save(category);
        return mapper.categoryToCategoryResponseDto(save);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new IllegalArgumentException("no category found with this" +
                "ID: "+categoryId));
        return mapper.categoryToCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {

        return mapper.categoriesToCategoryResponseDtos(
                StreamSupport.stream(categoryRepository.findAll().spliterator(),false).collect(Collectors.toList())
        );
    }

    @Override
    public CategoryResponseDto deleteCategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new IllegalArgumentException("no category found with this" +
                "ID: "+categoryId));
        categoryRepository.delete(category);
        return mapper.categoryToCategoryResponseDto(category);
    }

    @Transactional
    @Override
    public CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Category categoryToEdit=getCategory(categoryId);
        categoryToEdit.setName(categoryRequestDto.getName());
        return mapper.categoryToCategoryResponseDto(categoryToEdit);
    }
}
