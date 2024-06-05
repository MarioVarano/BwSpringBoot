package it.epicode.Bwspring.repositories;

import it.epicode.Bwspring.entities.Cliente;
import it.epicode.Bwspring.entities.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtentiRepository extends JpaRepository<Utenti, Long>, PagingAndSortingRepository<Utenti, Long> {

    Optional<Utenti> findOneByUsernameAndPassword(String username, String password);
    Optional<Utenti> findOneByUsername(String username);
}
