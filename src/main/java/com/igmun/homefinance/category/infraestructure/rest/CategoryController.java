package com.igmun.homefinance.category.infraestructure.rest;

import com.igmun.homefinance.category.application.AddSubCategoryService;
import com.igmun.homefinance.category.application.CreateCategoryService;
import com.igmun.homefinance.category.application.DeleteCategoryService;
import com.igmun.homefinance.category.application.GetAllCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v0/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final GetAllCategoriesService getAllCategoriesService;
  private final CreateCategoryService createCategoryService;
  private final DeleteCategoryService deleteCategoryService;
  private final AddSubCategoryService addSubCategoryService;
  private final CategoryMapper categoryMapper;

  @GetMapping
  public ListCategoryResponse retrieveCategories() {
    List<CategoryDto> categories = categoryMapper.fromCategoryList(getAllCategoriesService.getAllCategories());
    return new ListCategoryResponse(categories);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
    createCategoryService.createCategory(createCategoryRequest.tag());
  }

  @PutMapping("/{tag}")
  @ResponseStatus(HttpStatus.CREATED)
  public void addSubCategory(
    @RequestBody AddSubCategoryRequest addSubCategoryRequest,
    @PathVariable String tag
  ) {
    addSubCategoryService.addSubCategory(tag, addSubCategoryRequest.tag());
  }

  @DeleteMapping("/{tag}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCategory(@PathVariable String tag) {
    String category = new String(Base64.getDecoder().decode(tag));
    deleteCategoryService.deleteCategory(category);
  }
}
