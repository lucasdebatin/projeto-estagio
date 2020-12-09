package br.com.iclass.mvc.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String detalhe;

    @Enumerated(EnumType.STRING)
    private Material materialPrimario;

    @Enumerated(EnumType.STRING)
    private Material materialSecundario;

    @Enumerated(EnumType.STRING)
    private ItemTipo tipo;

    private Double peso;

    private int bonusDestreza;

    private int bonusResistencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public Material getMaterialPrimario() {
        return materialPrimario;
    }

    public void setMaterialPrimario(Material materialPrimario) {
        this.materialPrimario = materialPrimario;
    }

    public Material getMaterialSecundario() {
        return materialSecundario;
    }

    public void setMaterialSecundario(Material materialSecundario) {
        this.materialSecundario = materialSecundario;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public int getBonusDestreza() {
        return bonusDestreza;
    }

    public void setBonusDestreza(int bonusDestreza) {
        this.bonusDestreza = bonusDestreza;
    }

    public int getBonusResistencia() {
        return bonusResistencia;
    }

    public void setBonusResistencia(int bonusResistencia) {
        this.bonusResistencia = bonusResistencia;
    }

    public ItemTipo getTipo(){ return tipo;}

    public void setTipo(ItemTipo tipo){ this.tipo = tipo; }

    public Item() {
    }

    public Item(String detalhe, Material materialPrimario, Material materialSecundario, Double peso, int bonusDestreza, int bonusResistencia, ItemTipo tipo) {
        this.detalhe = detalhe;
        this.materialPrimario = materialPrimario;
        this.materialSecundario = materialSecundario;
        this.peso = peso;
        this.bonusDestreza = bonusDestreza;
        this.bonusResistencia = bonusResistencia;
        this.tipo = tipo;
    }
}
