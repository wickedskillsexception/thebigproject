package com.siit.mvc;

import com.siit.thebigproject.base.User;
import com.siit.thebigproject.exceptions.ValidationException;
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
@RequestMapping("/user")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger("UserController");

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("listUser");


        Collection<User> users = userService.listAll();
        result.addObject("users", users);

        return result;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("addUser");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }


    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        User user = userService.get(id);
        ModelAndView modelAndView = new ModelAndView("addUser");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String delete(long id) {
        userService.delete(id);
        return "redirect:/user";
    }

    @RequestMapping("/save")
    public ModelAndView save(@Valid User user,
                             BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            try {
                userService.save(user);
                RedirectView redirectView = new RedirectView("/user");
                modelAndView.setView(redirectView);
            } catch (ValidationException ex) {

                LOGGER.error("validation error", ex);

                List<String> errors = new LinkedList<>();
                errors.add(ex.getMessage());
                modelAndView = new ModelAndView("addUser");
                modelAndView.addObject("errors", errors);
                modelAndView.addObject("user", user);
            }

        } else {
            List<String> errors = new LinkedList<>();

            for (FieldError error :
                    bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ":" + error.getCode());
            }

            modelAndView = new ModelAndView("addUser");
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("user", user);
        }

        return modelAndView;
    }

}
