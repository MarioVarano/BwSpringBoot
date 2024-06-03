package it.epicode.Bwspring.repositories;

import it.epicode.Bwspring.entities.Cliente;
import it.epicode.Bwspring.entities.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtentiRepository extends JpaRepository<Utenti, Long>, PagingAndSortingRepository<Utenti, Long> {
}
