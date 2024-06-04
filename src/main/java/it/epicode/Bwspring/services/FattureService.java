package it.epicode.Bwspring.services;


import it.epicode.Bwspring.controllers.records.FattureRequest;
import it.epicode.Bwspring.entities.Fatture;
import it.epicode.Bwspring.entities.StatoFattura;
import it.epicode.Bwspring.repositories.FattureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FattureService {

    @Autowired
    FattureRepository fattureRep;

    public Fatture save(FattureRequest f) {
        StatoFattura stato = Optional.ofNullable(f.stato())
                .map(statoStr -> new StatoFattura(statoStr.toString()))
                .orElse(new StatoFattura());

        Fatture fatt = Fatture.builder()
                .withData(f.data())
                .withImporto(f.importo())
                .withNumero(f.numero())
                .withStato(f.stato())
                .build();

        try {
            return fattureRep.save(fatt);
        } catch (Exception e) {
            log.error("Errore", e);

            return null;
        }

    }

    public Fatture update(Long id,Fatture f){
        Fatture fatt = fattureRep.findById(id).orElseThrow(() -> new RuntimeException("La fattura che cerca non c'è"));
        if(fatt.getData() != null) fatt.setData(f.getData());
        if(fatt.getImporto() != 0) fatt.setImporto(f.getImporto());
        if(fatt.getNumero()!= null) fatt.setNumero(f.getNumero());
        if(fatt.getStato()!= null) fatt.setStato(f.getStato());
        fattureRep.save(fatt);
        return fatt;
    }
}
