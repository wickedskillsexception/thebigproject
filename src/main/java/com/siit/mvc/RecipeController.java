package com.siit.mvc;

import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.domain.User;
import com.siit.thebigproject.exceptions.ValidationException;
import com.siit.thebigproject.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    private static Logger LOGGER = LoggerFactory.getLogger("RecipeController");

    @Autowired
    private RecipeService recipeService;

    @RequestMapping("")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("recipe/list");


        Collection<Recipe> recipes = recipeService.listAll();
        result.addObject("recipes", recipes);

        return result;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("recipe/add");
        modelAndView.addObject("recipe", new Recipe());
        return modelAndView;
    }

    @RequestMapping("/view")
    public ModelAndView view(Long id) {
        Recipe recipe = recipeService.get(id);
        ModelAndView modelAndView = new ModelAndView("recipe/view");
        modelAndView.addObject("recipe", recipe);
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        Recipe recipe = recipeService.get(id);
        ModelAndView modelAndView = new ModelAndView("recipe/add");
        modelAndView.addObject("recipe", recipe);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String delete(long id) {
        recipeService.delete(id);
        return "redirect:/recipe";
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid Recipe recipe,
                             BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                recipeService.save(recipe);
                RedirectView redirectView = new RedirectView("/recipe");
                modelAndView.setView(redirectView);
            } catch (ValidationException ex) {

                LOGGER.error("validation error", ex);

                List<String> errors = new LinkedList<>();
                errors.add(ex.getMessage());
                modelAndView = new ModelAndView("recipe/add");
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("recipe", recipe);
            }

        } else {
            List<String> errors = new LinkedList<>();

            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getCode());
            }

            modelAndView = new ModelAndView("recipe/add");
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("recipe", recipe);
        }

        return modelAndView;
    }
}
