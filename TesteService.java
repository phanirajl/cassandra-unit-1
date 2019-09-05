package br.com.marley.statemachine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TesteService {

    private final TesteRepository rp;

    public void save(Teste t) {
        rp.save(t);
    }

    public Optional<Teste> get(UUID id) {
       return rp.findById(id);
    }

}
