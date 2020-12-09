package br.com.iclass.mvc.repository;

import br.com.iclass.mvc.entity.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}
