package it.epicode.Bwspring.controllers;

import it.epicode.Bwspring.entities.Provincia;
import it.epicode.Bwspring.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {


    @Autowired
    ProvinciaService province;

    @Value("${province_file}")
    private String provinceFile;



    @PostMapping("/load")
    public String importProvince() throws IOException {
        province.save(Path.of(provinceFile));
        return "Province importate con successo dal percorso file!";
    }

    @GetMapping
    public List<Provincia> getProvincia() throws IOException {
        return province.findAll();
    }
}
