package com.igmun.homefinance.category.infraestructure.rest;

import com.igmun.homefinance.category.application.AddSubCategoryService;
import com.igmun.homefinance.category.application.CreateCategoryService;
import com.igmun.homefinance.category.application.DeleteCategoryService;
import com.igmun.homefinance.category.application.GetAllCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
  public ListCategoryResponse getGroups() {
    List<CategoryDto> categories = categoryMapper.fromCategoryList(getAllCategoriesService.getAllCategories());
    return new ListCategoryResponse(categories);
  }

  @GetMapping("/{groupTag}")
  public ListCategoryResponse getCategories(@PathVariable String groupTag) {
    String tag = new String(Base64.getDecoder().decode(groupTag));
    List<CategoryDto> categories = categoryMapper.fromCategoryList(getAllCategoriesService.findByParentTag(tag));
    return new ListCategoryResponse(categories);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
    createCategoryService.createCategory(createCategoryRequest.tag());
  }

  @PostMapping("/{group}")
  @ResponseStatus(HttpStatus.CREATED)
  public void addSubCategory(
    @RequestBody AddSubCategoryRequest addSubCategoryRequest,
    @PathVariable String group
  ) {
    group = new String(Base64.getDecoder().decode(group));
    addSubCategoryService.addSubCategory(group, addSubCategoryRequest.tag());
  }

  @DeleteMapping("/{tag}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCategory(@PathVariable String tag) {
    String category = new String(Base64.getDecoder().decode(tag));
    deleteCategoryService.deleteCategory(category);
  }
}
