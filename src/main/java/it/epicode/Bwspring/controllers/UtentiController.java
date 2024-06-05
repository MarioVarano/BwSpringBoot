package it.epicode.Bwspring.controllers;

import it.epicode.Bwspring.controllers.exceptions.FieldValidationException;
import it.epicode.Bwspring.controllers.models.LoginModel;
import it.epicode.Bwspring.controllers.models.RegisterUserModel;
import it.epicode.Bwspring.services.UtentiService;
import it.epicode.Bwspring.services.dto.RegisterUserDto;
import it.epicode.Bwspring.services.dto.RegisteredUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.Optional;

@RestController
@RequestMapping("/utenti")
public class UtentiController {




    @Autowired
    UtentiService utentiService;


    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Validated RegisterUserModel model, //
                                      BindingResult validation, UriComponentsBuilder uri) {

        if (validation.hasErrors()) {
            throw new FieldValidationException(validation.getAllErrors());
        }

        var r = utentiService.register(RegisterUserDto.builder()
                .withUsername(model.username())
                .withEmail(model.email())
                .withPassword(model.password())
                .withNome(model.nome())
                .withCognome(model.cognome())
                .withAvatar(model.avatar())
                .withRuoli(String.valueOf(model.roles()))
                .build());

        var headers = new HttpHeaders();
        headers.add("Location", uri.path("/api/users/{id}").buildAndExpand(r.getId()).toString());
        return new ResponseEntity<>(r, headers, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginModel model, //
                                   BindingResult validation) {
        if (validation.hasErrors()) {
            throw new FieldValidationException(validation.getAllErrors());
        }

        return new ResponseEntity<>(utentiService.login(model.username(), model.password()).orElseThrow(), //
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<RegisteredUserDto> get(@PathVariable long id) {
        return utentiService.get(id);
    }
}
