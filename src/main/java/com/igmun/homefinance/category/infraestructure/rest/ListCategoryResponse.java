package com.igmun.homefinance.category.infraestructure.rest;

import java.util.Collection;

public record ListCategoryResponse(Collection<CategoryDto> categoryDtoList) {
}
