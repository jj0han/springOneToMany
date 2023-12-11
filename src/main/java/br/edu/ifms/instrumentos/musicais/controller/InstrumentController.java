package br.edu.ifms.instrumentos.musicais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.instrumentos.musicais.exception.InstrumentNotFoundException;
import br.edu.ifms.instrumentos.musicais.model.Instrument;
import br.edu.ifms.instrumentos.musicais.service.InstrumentService;
import jakarta.validation.Valid;

@Controller
public class InstrumentController {
    @Autowired
    private InstrumentService instrumentService;

    @GetMapping("/")
    public String listIntruments(Model model) {
        List<Instrument> instruments = instrumentService.findAllInstruments();
        model.addAttribute("listInstruments", instruments);
        return "/listInstruments";
    }

    @PostMapping("/search")
    public String searchInstruments(Model model, @Param("name") String name) {
        if (name == null) {
            return "redirect:/";
        }
        List<Instrument> instruments = instrumentService.findAllInstrumentsByName(name);
        model.addAttribute("listInstruments", instruments);
        return "/listInstruments";
    }

    @GetMapping("/new")
    public String newInstrument(Model model) {
        Instrument instrument = new Instrument();
        model.addAttribute("newInstrument", instrument);
        return "/createInstrument";
    }

    @PostMapping("/save")
    public String saveInstrument(@ModelAttribute("newInstrument") @Valid Instrument instrument,
            BindingResult erros,
            RedirectAttributes attributes) {
        if (erros.hasErrors()) {
            return "/createInstrument";
        }
        instrumentService.createInstrument(instrument);
        attributes.addFlashAttribute("message", "Instrumento salvo com sucesso!");
        return "redirect:/new";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstrument(@PathVariable("id") long id, RedirectAttributes attributes) {
        try {
            instrumentService.deleteInstrument(id);
        } catch (InstrumentNotFoundException e) {
            attributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") long id, RedirectAttributes attributes,
            Model model) {
        try {
            Instrument instrument = instrumentService.findInstrumentById(id);
            model.addAttribute("objectInstrument", instrument);
            return "/updateInstrument";
        } catch (InstrumentNotFoundException e) {
            attributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateInstrument(@PathVariable("id") long id,
            @ModelAttribute("objectInstrument") @Valid Instrument instrument,
            BindingResult erros) {
        if (erros.hasErrors()) {
            instrument.setId(id);
            return "/updateInstrument";
        }
        instrumentService.updateInstrument(instrument);
        return "redirect:/";
    }
}
