package br.com.iclass.mvc.controller;

import br.com.iclass.mvc.entity.Item;
import br.com.iclass.mvc.entity.ItemTipo;
import br.com.iclass.mvc.entity.Material;
import br.com.iclass.mvc.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ItemController {

    private ItemRepository itemRepository;
    private Item item;
    private Material[] materials;
    private ItemTipo[] tipos;

    @Autowired
    public ItemController(ItemRepository prItemRepository) {
        this.itemRepository = prItemRepository;
        materials = Material.values();
        tipos = ItemTipo.values();
    }

    @RequestMapping(value = "/adicionaItem", method = RequestMethod.POST)
    public String adicionaPersonagem(@ModelAttribute Item item, BindingResult errors, Model model) {
        itemRepository.save(item);

        return "redirect:/listarItens";
    }


    @RequestMapping(value = "/listarItens", method = RequestMethod.GET)
    public String listaPersonagens(Model model) {
        model.addAttribute("materials", materials);
        model.addAttribute("tipos", tipos);
        item = new Item();

        model.addAttribute("item", item);

        List<Item> listaItens = itemRepository.findAll();
        if (listaItens != null) {
            model.addAttribute("itens", listaItens);
        }
        return "/paginas/item/listarItens";
    }

}
