package aidian3k.springframework.springproject.recipeproject.repositories;

import aidian3k.springframework.springproject.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
