package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaController {
    private final String arquivo = "data/banco.dat";
    private ArrayList<Pessoa> pessoas;

    public PessoaController() {
        pessoas = carregar();
    }

    public void adicionar(Pessoa p) {
        pessoas.add(p);
        salvar();
    }

    public List<Pessoa> listar() {
        return pessoas;
    }

    public Pessoa buscarPorId(int id) {
        return pessoas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public boolean removerPorId(int id) {
        Pessoa pessoa = buscarPorId(id);
        if (pessoa != null) {
            pessoas.remove(pessoa);
            salvar();
            return true;
        }
        return false;
    }

    public boolean editarPessoa(int id, String novoNome, int novaIdade, String novoCampo) {
        Pessoa pessoa = buscarPorId(id);
        if (pessoa != null) {
            pessoa.setNome(novoNome);
            pessoa.setIdade(novaIdade);
            if (pessoa instanceof Aluno) {
                ((Aluno) pessoa).setCurso(novoCampo);
            } else if (pessoa instanceof Professor) {
                ((Professor) pessoa).setDisciplina(novoCampo);
            }
            salvar();
            return true;
        }
        return false;
    }

    private void salvar() {
        try {
            File diretorio = new File("data");
            if (!diretorio.exists()) {
                diretorio.mkdirs(); // Cria a pasta 'data' se não existir
            }

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
            out.writeObject(pessoas);
            out.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }


    private ArrayList<Pessoa> carregar() {
        File file = new File(arquivo);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Pessoa>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
