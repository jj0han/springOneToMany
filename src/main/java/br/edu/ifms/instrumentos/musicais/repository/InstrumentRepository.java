package br.edu.ifms.instrumentos.musicais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.instrumentos.musicais.model.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    List<Instrument> findByNameContainingIgnoreCase(String name);
}
