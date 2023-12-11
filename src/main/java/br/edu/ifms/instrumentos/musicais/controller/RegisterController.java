package br.edu.ifms.instrumentos.musicais.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.instrumentos.musicais.exception.InstrumentNotFoundException;
import br.edu.ifms.instrumentos.musicais.model.Category;
import br.edu.ifms.instrumentos.musicais.model.Instrument;
import br.edu.ifms.instrumentos.musicais.service.CategoryService;
import br.edu.ifms.instrumentos.musicais.service.InstrumentService;
import br.edu.ifms.instrumentos.musicais.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register/{id}")
    public String editarForm(@PathVariable("id") long id,
            RedirectAttributes attributes,
            Model model) {
        try {
            Instrument instrument = instrumentService.findInstrumentById(id);
            model.addAttribute("instrument", instrument);

            List<Category> categories = categoryService.findAllCategories();
            model.addAttribute("categories", categories);

            List<Category> categoriesRegister = categoryService.finsAllCategoriesInstrumentsRegister(id);
            model.addAttribute("categoriesInstrument", categoriesRegister);

            return "/register";
        } catch (InstrumentNotFoundException e) {
            attributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/createRegister/{idInstrument}")
    public String createRegisterinstrument(@PathVariable("idInstrument") long id,
            @RequestParam(value = "idCategories", required = false) long[] idscategories) {

        try {
            Instrument instrument = instrumentService.findInstrumentById(id);

            List<Long> listaCategory = new ArrayList<Long>();

            if (idscategories != null) {
                listaCategory = LongStream.of(idscategories)
                        .boxed()
                        .collect(Collectors.toList());
            }

            List<Category> categories = categoryService.findAllCategoriesById(listaCategory);
            registerService.createRegister(instrument, categories);

        } catch (InstrumentNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
