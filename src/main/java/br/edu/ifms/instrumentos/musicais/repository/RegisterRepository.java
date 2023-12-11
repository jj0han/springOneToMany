package br.edu.ifms.instrumentos.musicais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifms.instrumentos.musicais.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    @Query("select m from  Register  m  where m.instrument.id=:idInstrument")
    public List<Register> findRegistersById(Long idInstrument);
}
