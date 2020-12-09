package br.com.iclass.mvc.application;

import br.com.iclass.mvc.entity.*;
import br.com.iclass.mvc.repository.ItemPersonagemRepository;
import br.com.iclass.mvc.repository.ItemRepository;
import br.com.iclass.mvc.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = { "br.com.iclass.mvc.entity" })
@EnableJpaRepositories(basePackages = { "br.com.iclass.mvc.repository" })
@ComponentScan(basePackages = {"br.com.iclass.mvc.controller"})
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    PersonagemRepository personagemRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private ItemPersonagemRepository itemPersonagemRepository;

    @Override
    public void run(String... args) throws Exception {
        Personagem personagem = new Personagem(Raca.HUMANO, Classe.GUERREIRO, "Bertold", Sexo.MASCULINO,
                1,0,0,16,12,10,14,10);
        personagemRepository.save(personagem);

        Item item = new Item("Machado de Batalha", Material.FERRO, null, 8D, 0,0,ItemTipo.ARMAMENTO);
        personagemRepository.save(personagem);
        salvaItemPersonagem(personagem, item, 10);

        item = new Item("Espada Elucidator", Material.FERRO, null, 10D, 2,0, ItemTipo.ARMAMENTO);
        salvaItemPersonagem(personagem, item, 50);

        /*
        Busca ItemPersonagem por Personagem Id

        List<ItemPersonagem> itensPersonagem = itemPersonagemRepository.buscarPorPersonagemId(1L);

        itensPersonagem.forEach(System.out::println);

        for (ItemPersonagem ip:itensPersonagem) {
            System.out.println(ip.toString());
        }
        */
    }

    private void salvaItemPersonagem(Personagem personagem, Item item, int durabilidadeItem) {
        itemRepository.save(item);
        ItemPersonagem itemPersonagem = new ItemPersonagem(item, personagem, 10);
        itemPersonagemRepository.save(itemPersonagem);


    }
}
