package com.luisfelipe.springwebdio.controller;

import javax.validation.Valid;

import com.luisfelipe.springwebdio.model.Jedi;
import com.luisfelipe.springwebdio.repositories.JediRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class JediController {
    @Autowired
    private JediRepository repository;

    public JediRepository getControllerRepository() {
        return this.repository;
    }

    @GetMapping("/jedi")
    public ModelAndView jedi() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        modelAndView.addObject("allJedi", repository.getAllJedi());
        return modelAndView;
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-jedi");
        modelAndView.addObject("jedi", new Jedi());

        return modelAndView;
    }

    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute final Jedi jedi, final BindingResult result, 
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "new-jedi";
        } else {
            repository.add(jedi);
            redirectAttributes.addFlashAttribute("message", "Cadastrado com sucesso");
            return "redirect:jedi";
        }
    }
}