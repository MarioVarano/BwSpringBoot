package it.epicode.Bwspring.controllers;

import it.epicode.Bwspring.entities.Provincia;
import it.epicode.Bwspring.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
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



    @PostMapping("/load")
    public String importProvince(@RequestParam("filePath") String filePath) throws IOException {
        province.save(Path.of(filePath));
        return "Province importate con successo dal percorso file!";
    }

    @GetMapping
    public List<Provincia> getProvincia() throws IOException {
        return province.findAll();
    }
}
