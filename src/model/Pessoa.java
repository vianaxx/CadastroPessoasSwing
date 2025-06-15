package model;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {
    private static int contador = 1;
    private int id;
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.id = contador++;
        this.nome = nome;
        this.idade = idade;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public abstract String getDescricao();
}
