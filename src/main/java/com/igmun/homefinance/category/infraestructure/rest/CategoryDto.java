package com.igmun.homefinance.category.infraestructure.rest;

import java.util.Set;

public record CategoryDto(String tag, String color, Set<String> subCategories) {
}
