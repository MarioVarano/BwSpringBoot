package it.epicode.Bwspring.repositories;

import it.epicode.Bwspring.entities.Ruoli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RuoliRepository extends JpaRepository<Ruoli, Long>, PagingAndSortingRepository<Ruoli, Long> {

    Optional<Ruoli> findOneByNome(String roleName);
    Optional<Ruoli> findByNome(String nome);
}
