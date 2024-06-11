package it.epicode.Bwspring.services;


import it.epicode.Bwspring.entities.Provincia;
import it.epicode.Bwspring.repositories.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@Slf4j
public class ProvinciaService {


    @Autowired
    ProvinceRepository provinciaRepository;


   static final Path file = Path.of("C:/Users/alexx/Desktop/Bwspring/Bwspring/src/main/resources/data/province-italiane.csv");


   public  List<Provincia>save(Path file) throws IOException {
       List<Provincia> province;
       try{
           province = Files.lines(file, StandardCharsets.ISO_8859_1)
                   .skip(1)
                   .map(line -> line.split(";"))
                   .map(line -> new Provincia(line[0], line[1], line[2]))
                   .toList();
           provinciaRepository.saveAll(province);
           log.info("Salvate");
       }catch (IOException e) {
           log.error("Errore durante il caricamento delle province dal file CSV", e);
           throw e;
       }
       return province;
   }

   public List<Provincia> findAll() {
       return provinciaRepository.findAll();
   }


}
