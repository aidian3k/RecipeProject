package aidian3k.springframework.springproject.recipeproject.service;

import aidian3k.springframework.springproject.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    int getNumberOfRecipes();
}
