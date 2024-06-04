package it.epicode.Bwspring.controllers;


import it.epicode.Bwspring.controllers.records.FattureRequest;
import it.epicode.Bwspring.entities.Fatture;
import it.epicode.Bwspring.services.FattureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/fatture")
public class FattureController {


    @Autowired
    FattureService fattureService;

    @PostMapping
    public ResponseEntity<Fatture> save(@Validated  @RequestBody FattureRequest f, BindingResult validator){
        if(validator.hasErrors()) {
            throw new RuntimeException("FATAL ERROR");
        }
        Fatture fatt = fattureService.save(f);
        return ResponseEntity.ok(fatt);

    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Fatture> update(@PathVariable Long id, @RequestBody Fatture fatture){
//
//        var fatt = fattureService.update(id,fatture);
//        return ResponseEntity.ok(fatt);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Fatture> update(@PathVariable Long id, @RequestBody Fatture f) {
        try {
            Fatture fatturaAggiornata = fattureService.update(id,f);
            return ResponseEntity.ok(fatturaAggiornata);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
//            log.error("Errore durante l'aggiornamento della fattura con ID " + id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
