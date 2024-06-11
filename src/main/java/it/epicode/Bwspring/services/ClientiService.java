package it.epicode.Bwspring.services;


import it.epicode.Bwspring.entities.Cliente;
import it.epicode.Bwspring.repositories.ClientiRepository;
import it.epicode.Bwspring.repositories.ComuneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientiService {

    @Autowired
    ComuneRepository comuneRepository;

    @Autowired
    ClientiRepository clientiRep;


    public Cliente crea(Cliente c){
        c.getIndirizzoLegale().cercaComune(comuneRepository);

        if (c.getIndirizzoOperativa() == null) {
            c.setIndirizzoOperativa(c.getIndirizzoLegale());
        }else{
            c.getIndirizzoOperativa().cercaComune(comuneRepository);
        }

        var cl = Cliente.builder()
                .withPec(c.getPec())
                .withPartitaIva(c.getPartitaIva())
                .withCognomeContatto(c.getCognomeContatto())
                .withNomeContatto(c.getNomeContatto())
                .withDataUltimoContatto(c.getDataUltimoContatto())
                .withFatturatoAnnuale(c.getFatturatoAnnuale())
                .withEmail(c.getEmail())
                .withEmailContatto(c.getEmailContatto())
                .withRagioneSociale(c.getRagioneSociale())
                .withTelefono(c.getTelefono())
                .withTelefonoContatto(c.getTelefonoContatto())

                //questi 3 vanno settati dopo i controlli
                .withIndirizzoLegale(c.getIndirizzoLegale())
                .withIndirizzoOperativa(c.getIndirizzoOperativa())
                .build();
        log.info("Cliente: {}", cl);
        return clientiRep.save(cl);
    }
}
