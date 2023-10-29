package com.igmun.homefinance.category.infraestructure.rest;

import com.igmun.homefinance.category.application.CreateCategoryService;
import com.igmun.homefinance.category.application.GetAllCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v0/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final GetAllCategoriesService getAllCategoriesService;
  private final CreateCategoryService createCategoryService;
  private final CategoryMapper categoryMapper;

  @GetMapping
  public ListCategoryResponse retrieveCategories() {
    List<CategoryDto> categoryDtos = categoryMapper.fromCategoryList(getAllCategoriesService.getAllCategories());
    return new ListCategoryResponse(categoryDtos);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createCategory(String tag) {
    createCategoryService.createCategory(tag);
  }
}
