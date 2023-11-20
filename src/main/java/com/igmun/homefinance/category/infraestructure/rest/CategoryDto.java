package com.igmun.homefinance.category.infraestructure.rest;

import java.util.Set;

public record CategoryDto(String tag, Set<String> subCategories) {
}
