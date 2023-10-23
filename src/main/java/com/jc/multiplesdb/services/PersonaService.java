package com.jc.multiplesdb.services;

import com.jc.multiplesdb.models.Personas;
import com.jc.multiplesdb.repository.IPersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private final IPersonaRepository personaRepository;

    public PersonaService(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Personas findByIdPersona(Long id){
        return personaRepository.findById(id).orElse(null);
    }
}
