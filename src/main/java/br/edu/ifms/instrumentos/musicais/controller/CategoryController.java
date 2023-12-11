package br.edu.ifms.instrumentos.musicais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.instrumentos.musicais.model.Category;
import br.edu.ifms.instrumentos.musicais.service.CategoryService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/new")
    public String newCategory(Model model) {
        Category category = new Category();
        model.addAttribute("newCategory", category);
        return "/createCategory";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("newCategory") @Valid Category category,
            BindingResult erros,
            RedirectAttributes attributes) {
        if (erros.hasErrors()) {
            return "/createCategory";
        }
        categoryService.createCategory(category);
        attributes.addFlashAttribute("message", "Categoria salvo com sucesso!");
        return "redirect:/category/new";
    }
}
