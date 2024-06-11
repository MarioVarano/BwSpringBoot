package it.epicode.Bwspring.controllers;


import it.epicode.Bwspring.entities.Cliente;
import it.epicode.Bwspring.services.ClientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clienti")
public class ClientiController {


    @Autowired
    ClientiService clientiService;


    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente c){

        Cliente cl =  clientiService.crea(c);
        return ResponseEntity.ok(cl);
    }
}
