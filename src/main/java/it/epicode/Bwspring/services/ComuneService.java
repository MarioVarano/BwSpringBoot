package it.epicode.Bwspring.services;


import it.epicode.Bwspring.entities.Comune;
import it.epicode.Bwspring.entities.Provincia;
import it.epicode.Bwspring.repositories.ComuneRepository;
import it.epicode.Bwspring.repositories.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ComuneService {

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    ProvinciaService provinciaService;

    @Autowired
    ComuneRepository comuneRepository;

    static final Path file = Path.of("C:/Users/alexx/Desktop/Bwspring/Bwspring/src/main/resources/data/province-italiane.csv");

    public List<Comune> save(Path file) throws IOException {
        List<Comune> comuni;
        try {

           List<Provincia> prov = provinciaService.findAll();
            comuni = Files.lines(file, StandardCharsets.ISO_8859_1)
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(line -> new Comune(line[0], line[2],provinceRepository.findByProvincia(line[3])))
                    .toList();
            
            comuneRepository.saveAll(comuni);
            log.info("Salvate");
        } catch (IOException e) {
            log.error("Errore durante il caricamento dei comuni dal file CSV", e);
            throw e;
        }
        return comuni;
    }
}
