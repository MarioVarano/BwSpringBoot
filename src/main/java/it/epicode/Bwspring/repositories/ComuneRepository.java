package it.epicode.Bwspring.repositories;

import it.epicode.Bwspring.entities.Comune;
import it.epicode.Bwspring.entities.Fatture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long>, PagingAndSortingRepository<Comune, Long> {

    Comune findByNome(String nome);

}
