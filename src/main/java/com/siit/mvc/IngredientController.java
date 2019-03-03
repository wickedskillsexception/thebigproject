package com.siit.mvc;

import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.exceptions.ValidationException;
import com.siit.thebigproject.service.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private static Logger LOGGER = LoggerFactory.getLogger("IngredientController");

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping("")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("templates/listIngredient");


        Collection<Ingredient> ingredients = ingredientService.listAll();
        result.addObject("ingredients", ingredients);

        return result;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("addIngredient");
        modelAndView.addObject("ingredient", new Ingredient());
        return modelAndView;
    }


    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        Ingredient ingredient = ingredientService.get(id);
        ModelAndView modelAndView = new ModelAndView("addIngredient");
        modelAndView.addObject("ingredient", ingredient);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String delete(long id) {
        ingredientService.delete(id);
        return "redirect:/ingredient";
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid Ingredient ingredient,
                             BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                ingredientService.save(ingredient);
                RedirectView redirectView = new RedirectView("/ingredient");
                modelAndView.setView(redirectView);
            } catch (ValidationException ex) {

                LOGGER.error("validation error", ex);

                List<String> errors = new LinkedList<>();
                errors.add(ex.getMessage());
                modelAndView = new ModelAndView("addIngredient");
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("ingredient", ingredient);
            }

        } else {
            List<String> errors = new LinkedList<>();

            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getCode());
            }

            modelAndView = new ModelAndView("addIngredient");
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("ingredient", ingredient);
        }

        return modelAndView;
    }

}
