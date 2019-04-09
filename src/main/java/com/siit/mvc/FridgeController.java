package com.siit.mvc;

import com.siit.thebigproject.dao.sql.SQLRecipesDAO;
import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.domain.Recipe;
import com.siit.thebigproject.domain.Suggestion;
import com.siit.thebigproject.domain.FridgeIngredient;
import com.siit.thebigproject.domain.Ingredient;
import com.siit.thebigproject.exceptions.ValidationException;
import com.siit.thebigproject.service.CoreApp;
import com.siit.thebigproject.service.FridgeIngredientService;
import com.siit.thebigproject.service.FridgeService;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fridge")
public class FridgeController {


    private static Logger LOGGER = LoggerFactory.getLogger("FridgeController");

    @Autowired
    private FridgeService fridgeService;
    @Autowired
    CoreApp runCoreApp;
    @Autowired
    SQLRecipesDAO sqlRecipesDAO;



    @Autowired
    private FridgeIngredientService fridgeIngredientService;

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping("")
    public ModelAndView list(Long id) {
        ModelAndView result = new ModelAndView("ingredient/list");

        Collection<FridgeIngredient> fridgeIngredients = fridgeIngredientService.getByFridgeId(1l);
        Collection<Ingredient> ingredients = new ArrayList<>();

        for (FridgeIngredient f: fridgeIngredients) {
            Ingredient in = ingredientService.get(f.getIngredientId());
            ingredients.add(in);
        }
        result.addObject("ingredients", ingredients);

        return result;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("fridge/add");
        modelAndView.addObject("fridge", new Fridge());
        return modelAndView;
    }

    @RequestMapping("/suggestions")
    public ModelAndView getSuggestions(Long id) {
        ModelAndView modelAndView = new ModelAndView("fridge/suggestions");
        Map<Double, Recipe> matches = runCoreApp.recipeMatcher(fridgeService.get(id), sqlRecipesDAO.getAll());
        List<Suggestion> suggestions = runCoreApp.createSuggestions(matches, fridgeService.get(id));
        modelAndView.addObject("suggestions", suggestions);
        return modelAndView;
    }


    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        Fridge fridge = fridgeService.get(id);
        ModelAndView modelAndView = new ModelAndView("fridge/add");
        modelAndView.addObject("fridge", fridge);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String delete(long id) {
        fridgeService.delete(id);
        return "redirect:/fridge";
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid Fridge fridge,
                             BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                fridgeService.save(fridge);
                RedirectView redirectView = new RedirectView("/fridge");
                modelAndView.setView(redirectView);
            } catch (ValidationException ex) {

                LOGGER.error("validation error", ex);

                List<String> errors = new LinkedList<>();
                errors.add(ex.getMessage());
                modelAndView = new ModelAndView("fridge/add");
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("fridge", fridge);
            }

        } else {
            List<String> errors = new LinkedList<>();

            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getCode());
            }

            modelAndView = new ModelAndView("fridge/add");
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("fridge", fridge);
        }

        return modelAndView;
    }

}

