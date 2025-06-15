package view;

import controller.PessoaController;
import model.Aluno;
import model.Pessoa;
import model.Professor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaSwing {
    private JFrame frame;
    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextField txtCampo;
    private JComboBox<String> tipoBox;
    private JTable tabela;
    private DefaultTableModel tableModel;
    private PessoaController controller;

    public TelaSwing() {
        controller = new PessoaController();
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Cadastro de Pessoas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new FlowLayout());

        tipoBox = new JComboBox<>(new String[]{"Aluno", "Professor"});
        txtNome = new JTextField(10);
        txtIdade = new JTextField(3);
        txtCampo = new JTextField(10);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnAtualizar = new JButton("Atualizar");

        formPanel.add(new JLabel("Tipo:"));
        formPanel.add(tipoBox);
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtNome);
        formPanel.add(new JLabel("Idade:"));
        formPanel.add(txtIdade);
        formPanel.add(new JLabel("Curso/Disciplina:"));
        formPanel.add(txtCampo);
        formPanel.add(btnCadastrar);
        formPanel.add(btnAtualizar);


        JLabel lblRemover = new JLabel("ID para remover:");
        JTextField txtRemoverId = new JTextField(5);
        JButton btnRemover = new JButton("Remover por ID");

        formPanel.add(lblRemover);
        formPanel.add(txtRemoverId);
        formPanel.add(btnRemover);

        frame.add(formPanel, BorderLayout.NORTH);


        String[] colunas = {"ID", "Nome", "Idade", "Tipo", "Curso/Disciplina"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabela = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabela);
        frame.add(scrollPane, BorderLayout.CENTER);

        atualizarTabela();


        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText();
            int idade;
            try {
                idade = Integer.parseInt(txtIdade.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Idade inválida.");
                return;
            }
            String campo = txtCampo.getText();
            String tipo = (String) tipoBox.getSelectedItem();

            Pessoa p;
            if (tipo.equals("Aluno")) {
                p = new Aluno(nome, idade, campo);
            } else {
                p = new Professor(nome, idade, campo);
            }

            controller.adicionar(p);
            atualizarTabela();
            limparCampos();
        });

        btnAtualizar.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                int id = Integer.parseInt(tableModel.getValueAt(linha, 0).toString());
                String novoNome = txtNome.getText();
                int novaIdade;
                try {
                    novaIdade = Integer.parseInt(txtIdade.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Idade inválida.");
                    return;
                }
                String novoCampo = txtCampo.getText();

                boolean atualizado = controller.editarPessoa(id, novoNome, novaIdade, novoCampo);
                if (atualizado) {
                    atualizarTabela();
                    limparCampos();
                    JOptionPane.showMessageDialog(frame, "Pessoa atualizada com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Erro ao atualizar.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione uma pessoa na tabela.");
            }
        });

        btnRemover.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtRemoverId.getText());
                boolean removido = controller.removerPorId(id);
                if (removido) {
                    atualizarTabela();
                    JOptionPane.showMessageDialog(frame, "Pessoa removida com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Pessoa com esse ID não foi encontrada.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "ID inválido. Digite um número inteiro.");
            }
        });

        tabela.getSelectionModel().addListSelectionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                txtNome.setText(tableModel.getValueAt(linha, 1).toString());
                txtIdade.setText(tableModel.getValueAt(linha, 2).toString());
                tipoBox.setSelectedItem(tableModel.getValueAt(linha, 3).toString());
                txtCampo.setText(tableModel.getValueAt(linha, 4).toString());
            }
        });

        frame.setVisible(true);
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Pessoa> lista = controller.listar();
        for (Pessoa p : lista) {
            String tipo, campo;
            if (p instanceof Aluno) {
                tipo = "Aluno";
                campo = ((Aluno) p).getCurso();
            } else {
                tipo = "Professor";
                campo = ((Professor) p).getDisciplina();
            }
            Object[] linha = {p.getId(), p.getNome(), p.getIdade(), tipo, campo};
            tableModel.addRow(linha);
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtCampo.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaSwing::new);
    }
}
