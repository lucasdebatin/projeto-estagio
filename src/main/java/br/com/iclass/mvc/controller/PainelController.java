package br.com.iclass.mvc.controller;

import br.com.iclass.mvc.entity.Personagem;
import br.com.iclass.mvc.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class PainelController {

    @Autowired
    private PersonagemRepository personagemRepository;

    private List<Personagem> destaques;
    private List<Personagem> personagens;

    public PainelController(){}

    public void selecionaDestaques(List<Personagem> destaques, List<Personagem> personagens){
        int maxNivel1, maxNivel2, maxNivel3;

        maxNivel1 = maxNivel2 = maxNivel3 = -1;
        for(int i = 0; i < personagens.size(); i++){
            if(personagens.get(i).getNivel() > maxNivel1){
                maxNivel3 = maxNivel2;
                maxNivel2 = maxNivel1;
                maxNivel1 = personagens.get(i).getNivel();
            }
            else if(personagens.get(i).getNivel() > maxNivel2){
                maxNivel3 = maxNivel2;
                maxNivel2 = personagens.get(i).getNivel();
            }
            else if(personagens.get(i).getNivel() > maxNivel3){
                maxNivel3 = personagens.get(i).getNivel();
            }

        }

        for(Personagem p: personagens){
            if(p.getNivel() == maxNivel1) destaques.add(p);
        }

        for(Personagem p: personagens){
            if(p.getNivel() == maxNivel2) destaques.add(p);
        }

        for(Personagem p: personagens){
            if(p.getNivel() == maxNivel3) destaques.add(p);
        }

    }

    @RequestMapping(value = "/painel", method = RequestMethod.GET)
    public String listaAventura(Model model) {
        personagens = new ArrayList<>();
        personagens = personagemRepository.findAll();
        destaques = new ArrayList<>();

        selecionaDestaques(destaques,personagens);
        model.addAttribute("destaques", destaques);

        return "/paginas/personagem/painel";
    }


}
