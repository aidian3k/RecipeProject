package aidian3k.springframework.springproject.recipeproject.controllers;

import aidian3k.springframework.springproject.recipeproject.domain.Recipe;
import aidian3k.springframework.springproject.recipeproject.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    RecipeController recipeController;

    IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    void testMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    void testConnectionId()  {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        try {
            mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/show"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getRecipes() {
        //given
        var recipeSet = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setCookTime(3);

        Recipe recipe2 = new Recipe();
        recipe1.setCookTime(1);

        recipeSet.add(recipe1);
        recipeSet.add(recipe2);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String expectedString = "index";
        int expectedNumberOfInvocations = 1;
        int expectedSize = recipeSet.size();

        //then
        assertEquals(expectedString, indexController.getRecipes(model));
        Mockito.verify(recipeService, Mockito.times(expectedNumberOfInvocations)).getRecipes();
        Mockito.verify(model, Mockito.times(expectedNumberOfInvocations)).addAttribute(eq("recipes"), argumentCaptor.capture());
    }
}