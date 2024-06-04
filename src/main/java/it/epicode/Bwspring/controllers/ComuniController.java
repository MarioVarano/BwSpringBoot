package it.epicode.Bwspring.controllers;

import it.epicode.Bwspring.services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/comuni")
public class ComuniController {

    @Autowired
    ComuneService comuneService;

    @Value("${comuni_file}")
    private String comuniFile;


    @PostMapping("/load")
    public String importComuni() throws IOException {
        comuneService.save(Path.of(comuniFile));
        return "Comuni importati con successo dal percorso file!";
    }
}
