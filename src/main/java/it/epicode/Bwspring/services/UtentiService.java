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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
            return mapRegisteredUser.map(utentiRep.save(u));
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




}
