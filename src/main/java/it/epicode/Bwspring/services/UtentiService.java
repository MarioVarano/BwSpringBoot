package it.epicode.Bwspring.services;


import it.epicode.Bwspring.config.JwtUtils;
import it.epicode.Bwspring.entities.Ruoli;
import it.epicode.Bwspring.entities.Utenti;
import it.epicode.Bwspring.repositories.RuoliRepository;
import it.epicode.Bwspring.repositories.UtentiRepository;
import it.epicode.Bwspring.services.dto.LoginResponseDto;
import it.epicode.Bwspring.services.dto.RegisterUserDto;
import it.epicode.Bwspring.services.dto.RegisteredUserDto;
import it.epicode.Bwspring.services.exceptions.InvalidLoginException;
import it.epicode.Bwspring.services.exceptions.PersistEntityException;
import it.epicode.Bwspring.services.exceptions.UtenteNonTrovatoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class UtentiService {


    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    UtentiRepository utentiRep;

    @Autowired
    PasswordEncoder encoder;


    @Autowired
    private RuoliRepository roles;

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private JwtUtils jwt;

    @Autowired
    Mapper<RegisterUserDto, Utenti> mapEntity;
    @Autowired
    Mapper<Utenti, RegisteredUserDto> mapRegisteredUser;
    @Autowired
    Mapper<Utenti, LoginResponseDto> mapLogin;



    public RegisteredUserDto register(RegisterUserDto user) {
        try {
            var u = mapEntity.map(user);
            var p = encoder.encode(user.getPassword());
            log.info("Password encrypted: {}", p);
            u.setPassword(p);
            if (user.getRuoli() != null)
                Stream.of(user.getRuoli().split(",")).forEach(r -> u.getRuoli().add(roles.findOneByNome(r) //
                        .orElse(roles.save(Ruoli.builder().withNome(r).build()))));

            var ut =  mapRegisteredUser.map(utentiRep.save(u));
            this.sendMailRegistrazione("alexxan@live.it");
            return ut;
        } catch (Exception e) {
            log.error(String.format("Exception saving user %s", user), e);
            throw new PersistEntityException(user);
        }
    }


    public Optional<LoginResponseDto> login(String username, String password) {
        try {
            var a = auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            a.getAuthorities();
            SecurityContextHolder.getContext().setAuthentication(a);

            var dto = mapLogin.map(utentiRep.findOneByUsername(username).orElseThrow());
            dto.setToken(jwt.generateToken(a));
            return Optional.of(dto);
        } catch (NoSuchElementException e) {
            log.error("User not found", e);
            throw new InvalidLoginException(username, password);
        } catch (AuthenticationException e) {
            log.error("Authentication failed", e);
            throw new InvalidLoginException(username, password);
        }
    }


    public Optional<RegisteredUserDto> get(long id) {
        try {
            return Optional.of(mapRegisteredUser.map(utentiRep.findById(id).orElseThrow()));
        } catch (Exception e) {
            log.error(String.format("User not found for id %s", id), e);
            return Optional.empty();
        }
    }
    public void sendMailRegistrazione(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Utente");
        message.setText("Registrazione Utente avvenuta con successo");

        javaMailSender.send(message);
    }


    public Utenti update(Long id,Utenti ut) {
        Utenti u = utentiRep.findById(id).orElseThrow(() -> new UtenteNonTrovatoException("Utente da modificare non trovato"));
        if(ut.getNome() != null) u.setNome(ut.getNome());
        if(ut.getCognome()!= null) u.setCognome(ut.getCognome());

        if (ut.getRuoli() != null ) {//
            for (Ruoli ruolo : ut.getRuoli()) {
                log.info(ut.getRuoli().toString());
                if (!u.getRuoli().contains(ruolo)) {


                    u.addRuolo(ruolo);
                    roles.save(Ruoli.builder().withNome(ruolo.getNome()).build());

                }
            }
        }
        if(ut.getAvatar()!= null) u.setAvatar(ut.getAvatar());
        if(ut.getEmail()!= null) u.setEmail(ut.getEmail());
        if(ut.getUsername()!= null) u.setUsername(ut.getUsername());
        utentiRep.save(u);
        return u;

    }



}
