package view;

import controller.PessoaController;
import model.*;

import java.util.Scanner;

public class MenuConsole {
    private final PessoaController controller = new PessoaController();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Cadastrar Professor");
            System.out.println("3 - Listar Pessoas");
            System.out.println("4 - Editar Pessoa por ID");
            System.out.println("5 - Remover Pessoa por ID");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> cadastrarProfessor();
                case 3 -> listar();
                case 4 -> editar();
                case 5 -> remover();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarAluno() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt(); scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        controller.adicionar(new Aluno(nome, idade, curso));
        System.out.println("Aluno cadastrado!");
    }

    private void cadastrarProfessor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt(); scanner.nextLine();
        System.out.print("Disciplina: ");
        String disciplina = scanner.nextLine();

        controller.adicionar(new Professor(nome, idade, disciplina));
        System.out.println("Professor cadastrado!");
    }

    private void listar() {
        for (Pessoa p : controller.listar()) {
            System.out.println("\nID: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Idade: " + p.getIdade());
            System.out.println("Descrição: " + p.getDescricao());
        }
    }

    private void editar() {
        System.out.print("ID da pessoa: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Pessoa p = controller.buscarPorId(id);
        if (p == null) {
            System.out.println("Pessoa não encontrada.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova idade: ");
        int idade = scanner.nextInt(); scanner.nextLine();
        String campo;
        if (p instanceof Aluno) {
            System.out.print("Novo curso: ");
            campo = scanner.nextLine();
        } else {
            System.out.print("Nova disciplina: ");
            campo = scanner.nextLine();
        }

        controller.editarPessoa(id, nome, idade, campo);
        System.out.println("Pessoa editada.");
    }

    private void remover() {
        System.out.print("ID da pessoa: ");
        int id = scanner.nextInt(); scanner.nextLine();
        boolean sucesso = controller.removerPorId(id);
        System.out.println(sucesso ? "Removido com sucesso." : "Pessoa não encontrada.");
    }
}
