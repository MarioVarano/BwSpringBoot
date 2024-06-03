package it.epicode.Bwspring.services;


import it.epicode.Bwspring.repositories.UtentiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UtentiService {


    @Autowired
    UtentiRepository UtentiRep;
}
