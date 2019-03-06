package com.siit.mvc;

import com.siit.thebigproject.domain.Fridge;
import com.siit.thebigproject.exceptions.ValidationException;
import com.siit.thebigproject.service.FridgeService;
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
@RequestMapping("/fridge")
public class FridgeController {


    private static Logger LOGGER = LoggerFactory.getLogger("FridgeController");

    @Autowired
    private FridgeService fridgeService;

    @RequestMapping("")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("fridge/list");


        Collection<Fridge> fridges = fridgeService.listAll();
        result.addObject("fridges", fridges);

        return result;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("fridge/add");
        modelAndView.addObject("fridge", new Fridge());
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

