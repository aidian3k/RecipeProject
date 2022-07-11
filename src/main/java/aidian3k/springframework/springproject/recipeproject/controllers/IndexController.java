package aidian3k.springframework.springproject.recipeproject.controllers;

import aidian3k.springframework.springproject.recipeproject.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/","/index",""})
    public String getRecipes(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
