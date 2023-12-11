package br.edu.ifms.instrumentos.musicais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifms.instrumentos.musicais.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT category.id, category.name "
            + "	  FROM category "
            + "  JOIN register "
            + "	ON category.id = register.category_id "
            + "  JOIN instrument "
            + "    ON register.instrument_id = instrument.id "
            + "   AND instrument.id=:instrumentId", nativeQuery = true)
    List<Category> finsAllCategoriesInstrumentsRegister(Long instrumentId);
}
