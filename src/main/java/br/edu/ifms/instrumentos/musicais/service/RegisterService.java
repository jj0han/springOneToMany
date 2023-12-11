package br.edu.ifms.instrumentos.musicais.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.instrumentos.musicais.model.Category;
import br.edu.ifms.instrumentos.musicais.model.Instrument;
import br.edu.ifms.instrumentos.musicais.model.Register;
import br.edu.ifms.instrumentos.musicais.repository.RegisterRepository;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    public void createRegister(Instrument instrument, List<Category> categories) {

        List<Register> registers = registerRepository.findRegistersById(instrument.getId());
        deleteRegisters(registers);

        for (Category c : categories) {
            Register register = new Register();
            register.setInstrument(instrument);
            register.setCategory(c);
            register.setData(LocalDateTime.now());
            registerRepository.save(register);
        }
    }

    public void deleteRegisters(List<Register> registers) {
        registerRepository.deleteAll(registers);
    }

}
