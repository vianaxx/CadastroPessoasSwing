package view;

import controller.PessoaController;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TelaSwing extends JFrame {
    private PessoaController controller = new PessoaController();

    private JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Aluno", "Professor"});
    private JTextField nomeField = new JTextField(15);
    private JTextField idadeField = new JTextField(5);
    private JTextField campoExtraField = new JTextField(15);

    private JButton cadastrarBtn = new JButton("Cadastrar");
    private JButton salvarEdicaoBtn = new JButton("Salvar Alterações");

    private JTable tabela;
    private DefaultTableModel modelo;

    private Integer idSelecionado = null;

    public TelaSwing() {
        setTitle("Cadastro de Pessoas");
        setSize(750, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel do formulário
        JPanel formPanel = new JPanel(new FlowLayout());
        formPanel.add(new JLabel("Tipo:"));
        formPanel.add(tipoCombo);
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Idade:"));
        formPanel.add(idadeField);
        formPanel.add(new JLabel("Curso/Disciplina:"));
        formPanel.add(campoExtraField);
        formPanel.add(cadastrarBtn);
        formPanel.add(salvarEdicaoBtn);

        salvarEdicaoBtn.setVisible(false); // escondido por padrão

        add(formPanel, BorderLayout.NORTH);

        // Tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Idade", "Tipo", "Curso/Disciplina"}, 0) {
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        atualizarTabela();

        // Eventos
        cadastrarBtn.addActionListener(e -> cadastrar());
        salvarEdicaoBtn.addActionListener(e -> salvarEdicao());

        // Clique simples: carregar para edição
        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = tabela.getSelectedRow();
                if (row >= 0) {
                    int id = (int) modelo.getValueAt(row, 0);
                    carregarPessoaParaEdicao(id);
                }
            }
        });
    }

    private void cadastrar() {
        try {
            String nome = nomeField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            String campo = campoExtraField.getText();
            String tipo = (String) tipoCombo.getSelectedItem();

            if (tipo.equals("Aluno")) {
                controller.adicionar(new Aluno(nome, idade, campo));
            } else {
                controller.adicionar(new Professor(nome, idade, campo));
            }

            limparCampos();
            atualizarTabela();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Idade inválida");
        }
    }

    private void salvarEdicao() {
        if (idSelecionado == null) return;

        try {
            String nome = nomeField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            String campo = campoExtraField.getText();

            boolean sucesso = controller.editarPessoa(idSelecionado, nome, idade, campo);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Pessoa atualizada!");
                limparCampos();
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Idade inválida");
        }
    }

    private void carregarPessoaParaEdicao(int id) {
        Pessoa p = controller.buscarPorId(id);
        if (p != null) {
            idSelecionado = id;
            nomeField.setText(p.getNome());
            idadeField.setText(String.valueOf(p.getIdade()));
            if (p instanceof Aluno) {
                tipoCombo.setSelectedItem("Aluno");
                campoExtraField.setText(((Aluno) p).getCurso());
            } else {
                tipoCombo.setSelectedItem("Professor");
                campoExtraField.setText(((Professor) p).getDisciplina());
            }

            cadastrarBtn.setVisible(false);
            salvarEdicaoBtn.setVisible(true);
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        idadeField.setText("");
        campoExtraField.setText("");
        tipoCombo.setSelectedIndex(0);
        idSelecionado = null;

        cadastrarBtn.setVisible(true);
        salvarEdicaoBtn.setVisible(false);
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        for (Pessoa p : controller.listar()) {
            String tipo = (p instanceof Aluno) ? "Aluno" : "Professor";
            String campo = (p instanceof Aluno) ? ((Aluno) p).getCurso() : ((Professor) p).getDisciplina();
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getIdade(), tipo, campo});
        }
    }
}
