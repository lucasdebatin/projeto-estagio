package br.com.iclass.mvc.controller;

import br.com.iclass.mvc.entity.Classe;
import br.com.iclass.mvc.entity.Personagem;
import br.com.iclass.mvc.entity.Raca;
import br.com.iclass.mvc.entity.Sexo;
import br.com.iclass.mvc.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PersonagemController extends BaseController {

    public static final String PAGINA_MANTER_PERSONAGEM = "/paginas/personagem/manterPersonagem";
    private PersonagemRepository personagemRepository;

    private Classe [] classes;
    private Raca [] racas;
    private Sexo[] sexos;

    String module() {
        return "personagens";
    }

    @Autowired
    public PersonagemController(PersonagemRepository personagemRepository) {
        this.personagemRepository = personagemRepository;

        classes = Classe.values();
        racas = Raca.values();
        sexos = Sexo.values();
    }

    @RequestMapping(value = "/listarPersonagens", method = RequestMethod.GET)
    public String listaPersonagens(Model model) {
        List<Personagem> listaPersonagens = personagemRepository.findAll();
        if (listaPersonagens != null) {
            model.addAttribute("personagens", listaPersonagens);
        }
        return "/paginas/personagem/listarPersonagens";
    }

    @RequestMapping(value = "/adicionaPersonagem", method = RequestMethod.GET)
    public String novoPersonagem(Model model) {
        Personagem personagem = new Personagem();
        carregaBaseEditarPersonagem(model, personagem);
        return PAGINA_MANTER_PERSONAGEM;
    }

    @RequestMapping(value = "/editaPersonagem/{id}", method = RequestMethod.GET)
    public String editaPersonagem(Model model, @PathVariable("id") Long id) {
        final Optional<Personagem> byId = personagemRepository.findById(id);
        Personagem personagem = byId.isPresent() ? byId.get() : new Personagem();
        carregaBaseEditarPersonagem(model, personagem);
        return PAGINA_MANTER_PERSONAGEM;
    }

    private void carregaBaseEditarPersonagem(Model model, Personagem personagem) {
        model.addAttribute("classes", classes);
        model.addAttribute("racas", racas);
        model.addAttribute("sexos", sexos);
        model.addAttribute("personagem", personagem);
    }

    @RequestMapping(value = "/adicionaPersonagem", method = RequestMethod.POST)
    public String adicionaPersonagem(@ModelAttribute @Valid Personagem personagem, BindingResult errors, Model model) {

        if (errors.hasErrors()) {
            carregaBaseEditarPersonagem(model, personagem);
            return PAGINA_MANTER_PERSONAGEM;
        }

        personagemRepository.save(personagem);

        return "redirect:/listarPersonagens";
    }


}
