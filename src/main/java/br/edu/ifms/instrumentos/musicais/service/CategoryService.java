package br.edu.ifms.instrumentos.musicais.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.instrumentos.musicais.model.Category;
import br.edu.ifms.instrumentos.musicais.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> finsAllCategoriesInstrumentsRegister(Long id) {
        return categoryRepository.finsAllCategoriesInstrumentsRegister(id);
    }

    public List<Category> findAllCategoriesById(Iterable<Long> ids) {
        return categoryRepository.findAllById(ids);
    }
}
