package aidian3k.springframework.springproject.recipeproject.service;

import aidian3k.springframework.springproject.recipeproject.commands.IngredientCommand;
import aidian3k.springframework.springproject.recipeproject.converters.IngredientToIngredientCommand;
import aidian3k.springframework.springproject.recipeproject.domain.Ingredient;
import aidian3k.springframework.springproject.recipeproject.domain.Recipe;
import aidian3k.springframework.springproject.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService{

    RecipeRepository recipeRepository;
    IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            log.error("Given recipe does not exist! ID:" + recipeId);
            throw new IllegalArgumentException("Found Id is null!");
        }

        Recipe recipe = recipeOptional.get();
        Set<Ingredient> ingredientSet = recipe.getIngredients();
        Ingredient ingredient = null;

        for(Ingredient ing : ingredientSet){
            if(ing.getId() == ingredientId) {
                ingredient = ing;
                break;
            }
        }

        if(ingredient == null) {
            log.error("Ingredient has not been found! ID:" + ingredientId);
            throw new IllegalArgumentException("Found Id is null!");
        }

        return ingredientToIngredientCommand.convert(ingredient);

    }

    @Override
    public IngredientCommand saveIngredientCommand() {
        return null;
    }
}
