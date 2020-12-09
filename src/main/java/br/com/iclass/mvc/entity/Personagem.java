package br.com.iclass.mvc.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Personagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Raca raca;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Classe classe;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private int nivel = 1;//Valor inicial

    private long experiencia = 0;//Valor inicial: 0

    private int pontos = 10;//Valor inicial

    @Min(0)
    private int forca;

    @Min(0)
    private int destreza;

    @Min(0)
    private int inteligencia;

    @Min(0)
    private int resistencia;

    @Min(0)
    private int sorte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public long getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(long experiencia) {
        this.experiencia = experiencia;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getSorte(){ return sorte; }

    public void setSorte(int sorte){ this.sorte = sorte; }

    public Personagem() {
    }

    public Personagem(Raca raca, Classe classe, String nome, Sexo sexo, int nivel, long experiencia, int pontos, int forca, int destreza, int inteligencia, int resistencia, int sorte) {
        this.raca = raca;
        this.classe = classe;
        this.nome = nome;
        this.sexo = sexo;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.pontos = pontos;
        this.forca = forca;
        this.destreza = destreza;
        this.inteligencia = inteligencia;
        this.resistencia = resistencia;
        this.sorte = sorte;
    }
}
