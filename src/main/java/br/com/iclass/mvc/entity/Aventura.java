package br.com.iclass.mvc.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
public class Aventura implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    private long xp = 0;
    private int data;
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public Aventura(){}
    public Aventura(Personagem personagem, long xp, int data){
        this.personagem = personagem;
        this.xp = xp;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Aventura{" +
                "id=" + id +
                ", personagem=" + personagem.getNome() +
                ", xp=" + xp +
                ". data=" + data +
                '}';
    }
}
