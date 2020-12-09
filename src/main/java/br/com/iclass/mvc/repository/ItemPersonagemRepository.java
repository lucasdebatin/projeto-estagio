package br.com.iclass.mvc.repository;

import br.com.iclass.mvc.entity.Item;
import br.com.iclass.mvc.entity.ItemPersonagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
public interface ItemPersonagemRepository extends JpaRepository<ItemPersonagem, Long> {
    @Transactional(readOnly = true)
    @Query("select ip from ItemPersonagem ip where ip.personagem.id = :personagemId")
    List<ItemPersonagem> buscarPorPersonagemId(@Param("personagemId") Long personagemId);

    @Query(" select ip.item " +
            " from ItemPersonagem ip " +
            " inner join ip.personagem personagem " +
            " where personagem.id = ?1 ")
    List<Item> getItensPorPersonagem(Long personagemId);

}
