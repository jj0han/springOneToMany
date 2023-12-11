package br.edu.ifms.instrumentos.musicais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.instrumentos.musicais.exception.InstrumentNotFoundException;
import br.edu.ifms.instrumentos.musicais.model.Instrument;
import br.edu.ifms.instrumentos.musicais.repository.InstrumentRepository;

@Service
public class InstrumentService {
    @Autowired
    private InstrumentRepository instrumentRepository;

    public Instrument createInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public List<Instrument> findAllInstruments() {
        return instrumentRepository.findAll();
    }

    public Instrument findInstrumentById(Long id) throws InstrumentNotFoundException {
        Optional<Instrument> opt = instrumentRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new InstrumentNotFoundException("Instrumento com id : " + id + " não existe");
        }
    }

    public void deleteInstrument(Long id) throws InstrumentNotFoundException {
        Instrument instrument = findInstrumentById(id);
        instrumentRepository.delete(instrument);
    }

    public Instrument updateInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public List<Instrument> findAllInstrumentsByName(String name) {
        return instrumentRepository.findByNameContainingIgnoreCase(name);
    }
}
