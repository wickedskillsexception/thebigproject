package com.siit.mvc;

import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.domain.FridgeIngredient;
import com.siit.thebigproject.domain.User;
import com.siit.thebigproject.exceptions.ValidationException;
import com.siit.thebigproject.service.FridgeIngredientService;
import com.siit.thebigproject.service.FridgeService;
import com.siit.thebigproject.service.IngredientService;
import com.siit.thebigproject.service.UserService;
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
@RequestMapping("/fridgeIngredient")
public class FridgeIngredientController {
    private static Logger LOGGER = LoggerFactory.getLogger("FridgeIngredientController");

    @Autowired
    private FridgeIngredientService fridgeIngredientService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private FridgeService fridgeService;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("fridgeIngredient/list");
        Collection<FridgeIngredient> fridgeIngredients = fridgeIngredientService.listAll();
        result.addObject("fridgeIngredients", fridgeIngredients);
        return result;
    }


    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("fridgeingredient/add");
        modelAndView.addObject("fridgeingredient", new FridgeIngredient());
        return modelAndView;
    }

    @RequestMapping("/addIngredient")
    public String add(Long id, String user_email) throws ValidationException {
        User user = userService.getByEmail(user_email);
        Fridge fridge = fridgeService.getByUserId(user.getId());
        FridgeIngredient fridgeIngredient = new FridgeIngredient(fridge.getId(), id);

        if (fridgeIngredientService.checkIfExists(fridgeIngredient))
            return "redirect:/ingredient";

        fridgeIngredientService.save(fridgeIngredient);
        return "redirect:/ingredient";
    }


    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        FridgeIngredient fridgeIngredient = fridgeIngredientService.get(id);
        ModelAndView modelAndView = new ModelAndView("fridgeIngredient/add");
        modelAndView.addObject("fridgeIngredient", fridgeIngredient);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String delete(long id) {
        fridgeIngredientService.delete(id);
        return "redirect:/fridge";
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid FridgeIngredient fridgeIngredient,
                             BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                fridgeIngredientService.save(fridgeIngredient);
                RedirectView redirectView = new RedirectView("/fridgeIngredient");
                modelAndView.setView(redirectView);
            } catch (ValidationException ex) {

                LOGGER.error("validation error", ex);

                List<String> errors = new LinkedList<>();
                errors.add(ex.getMessage());
                modelAndView = new ModelAndView("fridgeIngredient/add");
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("fridgeIngredient", fridgeIngredient);
            }

        } else {
            List<String> errors = new LinkedList<>();

            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getCode());
            }

            modelAndView = new ModelAndView("fridgeIngredient/add");
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("fridgeIngredient", fridgeIngredient);
        }

        return modelAndView;
    }
}
