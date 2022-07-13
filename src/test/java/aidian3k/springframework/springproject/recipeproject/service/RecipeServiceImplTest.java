package aidian3k.springframework.springproject.recipeproject.service;

import aidian3k.springframework.springproject.recipeproject.domain.Recipe;
import aidian3k.springframework.springproject.recipeproject.repositories.RecipeRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }
    @Test
    void getRecipes() {
        //given
        Recipe recipe = new Recipe();
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(recipe);

        //when
        int expectedNumberOfInvocations = 1;
        int expectedSizeOfSet = 1;

        //then
        when(recipeRepository.findAll()).thenReturn(recipeSet);
        assertEquals(expectedSizeOfSet, recipeSet.size());
    }

    @Test
    void findByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

}