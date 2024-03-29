package aidian3k.springframework.springproject.recipeproject.repositories;

import aidian3k.springframework.springproject.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
