package it.epicode.Bwspring.services;


import it.epicode.Bwspring.controllers.records.FattureRequest;
import it.epicode.Bwspring.entities.Cliente;
import it.epicode.Bwspring.entities.Fatture;
import it.epicode.Bwspring.entities.StatoFattura;
import it.epicode.Bwspring.repositories.ClientiRepository;
import it.epicode.Bwspring.repositories.FattureRepository;
import it.epicode.Bwspring.services.exceptions.FatturaNonTrovataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class FattureService {

    @Autowired
    FattureRepository fattureRep;

    @Autowired
    ClientiRepository clientiRep;

    public Fatture save(FattureRequest f,Long idCliente) {
        StatoFattura stato = Optional.ofNullable(f.stato())
                .map(statoStr -> new StatoFattura(statoStr.toString()))
                .orElse(new StatoFattura());
        Cliente c = clientiRep.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente non trovato"));
        if(c != null){
            Fatture fatt = Fatture.builder()
                    .withData(f.data())
                    .withImporto(f.importo())
                    .withNumero(f.numero())
                    .withStato(f.stato())
                    .withCliente(c)
                    .build();
            return fattureRep.save(fatt);

        }else{
          throw new RuntimeException("Impossibile creare fattura");
        }

    }

    public Fatture update(Long id,Fatture f){
        Fatture fatt = fattureRep.findById(id).orElseThrow(() -> new FatturaNonTrovataException("La fattura che cerca non c'è"));
        if(fatt.getData() != null) fatt.setData(f.getData());
        if(fatt.getImporto() != 0) fatt.setImporto(f.getImporto());
        if(fatt.getNumero()!= null) fatt.setNumero(f.getNumero());
        if(fatt.getStato()!= null) fatt.setStato(f.getStato());
        fattureRep.save(fatt);
        return fatt;
    }

    public Fatture delete(Long id){
        var fatt = fattureRep.findById(id).orElseThrow(() -> new FatturaNonTrovataException("La fattura che cerca non c'è"));
        fattureRep.deleteById(id);
        return fatt;
    }

    public Page<Fatture> getFatture(Pageable pageable) {
        return fattureRep.findAll( pageable);
    }
}
