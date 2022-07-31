package aidian3k.springframework.springproject.recipeproject.controllers;

import aidian3k.springframework.springproject.recipeproject.commands.RecipeCommand;
import aidian3k.springframework.springproject.recipeproject.exceptions.RecipeNotFoundException;
import aidian3k.springframework.springproject.recipeproject.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        try {
            Long.parseLong(id);
        } catch(NumberFormatException e ) {
            throw new NumberFormatException("NumberFormatException has been thrown for id: " + id);
        }

        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(Model model, @PathVariable String id){
        try {
            Long.parseLong(id);
        } catch(NumberFormatException e ) {
            throw new NumberFormatException("NumberFormatException has been thrown for id: " + id);
        }

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        try {
            Long.parseLong(id);
        } catch(NumberFormatException e ) {
            throw new NumberFormatException("NumberFormatException has been thrown for id: " + id);
        }

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @PostMapping
    @RequestMapping( "recipe" )
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecipeNotFoundException.class)
    public ModelAndView recipeNotFoundExceptionHandler(RecipeNotFoundException exception){
        log.error("The id has not been found!");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
