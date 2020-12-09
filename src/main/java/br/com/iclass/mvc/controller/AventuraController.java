package br.com.iclass.mvc.controller;

import br.com.iclass.mvc.entity.Aventura;
import br.com.iclass.mvc.entity.ItemPersonagem;
import br.com.iclass.mvc.entity.Personagem;
import br.com.iclass.mvc.repository.ItemPersonagemRepository;
import br.com.iclass.mvc.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class AventuraController {

    private Aventura aventura;

    @Autowired
    private PersonagemRepository personagemRepository;
    @Autowired
    private ItemPersonagemRepository itemPersonagemRepository;

    private List<Personagem> personagems;
    private List<ItemPersonagem> itemPersonagens;
    @Autowired
    public AventuraController(){

    }

    //XP do personagem é "gasto" para passar de nivel
    public void atualizaDados(Aventura aventura){
        int nivelPersonagem = aventura.getPersonagem().getNivel();
        int pontosPersonagem = aventura.getPersonagem().getPontos();
        long xpPersonagem = aventura.getPersonagem().getExperiencia();
        xpPersonagem += aventura.getXp();

        while(xpPersonagem >= (nivelPersonagem+1)*100){
            xpPersonagem -= (nivelPersonagem+1)*100;
            nivelPersonagem++;
            if ((nivelPersonagem) >= 10 && (nivelPersonagem) % 10 == 0) pontosPersonagem += 3;
            else if ((nivelPersonagem) >= 5 && (nivelPersonagem) % 5 == 0) pontosPersonagem += 2;
            else pontosPersonagem += 1;
        }
        aventura.getPersonagem().setExperiencia(xpPersonagem);
        aventura.getPersonagem().setNivel(nivelPersonagem);
        aventura.getPersonagem().setPontos(pontosPersonagem);
    }

    public void atualizaItens(List<ItemPersonagem> itemPersonagens){

        Random rand = new Random();
        double num;

        for(int i = 0; i < itemPersonagens.size(); i++){
            if(rand.nextInt(10) == 0 && itemPersonagens.get(i).getDurabilidade() > 0){
                num = rand.nextDouble();
                if(num <= 0.4){
                    itemPersonagens.get(i).setDurabilidade(itemPersonagens.get(i).getDurabilidade() - 1);
                    itemPersonagemRepository.save(itemPersonagens.get(i));
                }
                else if(num>0.4 && num <= 0.6){
                    itemPersonagens.get(i).setDurabilidade(itemPersonagens.get(i).getDurabilidade() - 2);
                    itemPersonagemRepository.save(itemPersonagens.get(i));
                }
                else if(num > 0.6 && num <= 0.7){
                    itemPersonagens.get(i).setDurabilidade(itemPersonagens.get(i).getDurabilidade() - 3);
                    itemPersonagemRepository.save(itemPersonagens.get(i));
            }
                else if(num > 0.7 && num <= 0.75){
                    itemPersonagens.get(i).setDurabilidade(itemPersonagens.get(i).getDurabilidade() - 4);
                    itemPersonagemRepository.save(itemPersonagens.get(i));
                }
                else if(num > 0.75 && num <= 1.0){
                    itemPersonagens.get(i).setDurabilidade(itemPersonagens.get(i).getDurabilidade() - 5);
                    itemPersonagemRepository.save(itemPersonagens.get(i));
                }
                else{
                    System.out.println("Erro no calculo da funcao de durabilidade");
                }

            }
        }
    }

    @RequestMapping(value = "/adicionaAventura", method = RequestMethod.POST)
    public String adicionaAventura(@ModelAttribute @Valid Aventura aventura, BindingResult errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("aventura", aventura);
            return "/paginas/personagem/listarAventura";
        }

        if(aventura.getPersonagem() == null){
            return "/paginas/personagem/listarAventura";
        }

        itemPersonagens = new ArrayList<>();
        itemPersonagens = itemPersonagemRepository.buscarPorPersonagemId(aventura.getPersonagem().getId());

        atualizaDados(aventura);
        atualizaItens(itemPersonagens);
        personagemRepository.save(aventura.getPersonagem());

        return "redirect:/listarAventura";
    }

    @RequestMapping(value = "/listarAventura", method = RequestMethod.GET)
    public String listaAventura(Model model) {

     /*   if (errors.hasErrors()) {
            model.addAttribute("aventura", aventura);
            return "/paginas/personagem/listarAventura";
        } */
        personagems = new ArrayList<>();
        personagems = personagemRepository.findAll();
        model.addAttribute("personagems", personagems);

        aventura = new Aventura();
        model.addAttribute("aventura", aventura);

        return "/paginas/personagem/listarAventura";
    }
}
