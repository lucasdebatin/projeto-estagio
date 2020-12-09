package br.com.iclass.mvc.controller;

import br.com.iclass.mvc.entity.Item;
import br.com.iclass.mvc.entity.ItemPersonagem;
import br.com.iclass.mvc.entity.Personagem;
import br.com.iclass.mvc.repository.ItemPersonagemRepository;
import br.com.iclass.mvc.repository.ItemRepository;
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

@Controller
@RequestMapping("/")
public class ItemPersonagemController {

    private ItemPersonagem itemPersonagem;
    private ItemPersonagemRepository repository;

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ItemRepository itemRepository;

    private List<Item> itens;
    private List<Personagem> personagems;

    @Autowired
    public ItemPersonagemController(ItemPersonagemRepository prItemPersonagemRepository) {
        this.repository = prItemPersonagemRepository;
    }

    @RequestMapping(value = "/adicionaItemPersonagem", method = RequestMethod.POST)
    public String adicionaPersonagem(@ModelAttribute @Valid ItemPersonagem itemPersonagem, BindingResult errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("itemPersonagem", itemPersonagem);
            return "/paginas/item/listarItemPersonagens";
        }

        repository.save(itemPersonagem);

        return "redirect:/listarItemPersonagens";
    }

    @RequestMapping(value = "/listarItemPersonagens", method = RequestMethod.GET)
    public String listaPersonagens(Model model) {
        personagems = new ArrayList<>();
        personagems = personagemRepository.findAll();
        model.addAttribute("personagems", personagems);

        itens = new ArrayList<>();
        itens = itemRepository.findAll();
        model.addAttribute("itens", itens);

        itemPersonagem = new ItemPersonagem();
        model.addAttribute("itemPersonagem", itemPersonagem);

        List<ItemPersonagem> listaItemPersonagens = repository.findAll();
        if (listaItemPersonagens != null) {
            model.addAttribute("itemPersonagens", listaItemPersonagens);
        }

        return "/paginas/item/listarItemPersonagens";
    }
}

