package br.com.iclass.mvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class ItemPersonagem implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    private int durabilidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }

    public ItemPersonagem() {
    }

    public ItemPersonagem(Item item, Personagem personagem, int durabilidade) {
        this.item = item;
        this.personagem = personagem;
        this.durabilidade = durabilidade;
    }

    @Override
    public String toString() {
        return "ItemPersonagem{" +
                "id=" + id +
                ", item=" + item.getDetalhe() +
                ", personagem=" + personagem.getNome() +
                ", durabilidade=" + durabilidade +
                '}';
    }
}
