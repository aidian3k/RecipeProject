package aidian3k.springframework.springproject.recipeproject.service;

import aidian3k.springframework.springproject.recipeproject.commands.IngredientCommand;
import aidian3k.springframework.springproject.recipeproject.converters.IngredientCommandToIngredient;
import aidian3k.springframework.springproject.recipeproject.converters.IngredientToIngredientCommand;
import aidian3k.springframework.springproject.recipeproject.domain.Ingredient;
import aidian3k.springframework.springproject.recipeproject.domain.Recipe;
import aidian3k.springframework.springproject.recipeproject.repositories.RecipeRepository;
import aidian3k.springframework.springproject.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService{

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()){
            log.error("Recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(ingredientCommandOptional.isEmpty()){
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(recipeOptional.isEmpty()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                recipe.addIngredient(Objects.requireNonNull(ingredientCommandToIngredient.convert(command)));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst()
                    .get());
        }

    }

    @Override
    public void deleteIngredientById(Long recipeId, Long ingredientId) {
        Recipe newRecipe = recipeRepository.findById(recipeId).get();
        Set<Ingredient> ingredientSet = newRecipe.getIngredients();
        Ingredient foundIngredient = ingredientSet.stream().filter(ingredient -> ingredient.getId() == ingredientId).findFirst().orElseThrow(() -> new IllegalArgumentException("No ingredient found!"));
        foundIngredient.setRecipe(null);
        newRecipe.getIngredients().remove(foundIngredient);
        recipeRepository.save(newRecipe);
    }
}
