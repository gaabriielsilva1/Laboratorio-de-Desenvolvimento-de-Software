/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CadastroPessoa extends JFrame {

    private final List<Pessoa> listaPessoas = new ArrayList<>();
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    private JTextField txtNome;
    private JTextField txtIdade;
    private JComboBox<String> cbSexo;
    private JTextField txtIdioma;

    public CadastroPessoa() {
        setTitle("Cadastro e Exportação de Pessoas (CSV)");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Painel Superior Ajustado para 3 Linhas
        JPanel painelFormulario = new JPanel(new GridLayout(3, 4, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Linha 1
        painelFormulario.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        painelFormulario.add(txtIdade);

        // Linha 2
        painelFormulario.add(new JLabel("Sexo:"));
        cbSexo = new JComboBox<>(new String[]{"Feminino", "Masculino", "Outro"});
        painelFormulario.add(cbSexo);

        painelFormulario.add(new JLabel("Idioma:"));
        txtIdioma = new JTextField();
        painelFormulario.add(txtIdioma);

        // Linha 3 (Botões)
        painelFormulario.add(new JLabel()); // Espaçador
        JButton btnAdicionar = new JButton("Adicionar");
        painelFormulario.add(btnAdicionar);

        painelFormulario.add(new JLabel()); // Espaçador
        JButton btnSalvarCsv = new JButton("Gerar CSV");
        btnSalvarCsv.setBackground(new Color(34, 139, 34));
        btnSalvarCsv.setForeground(Color.WHITE);
        painelFormulario.add(btnSalvarCsv);

        add(painelFormulario, BorderLayout.NORTH);

        // Painel Central (Tabela com nova coluna)
        String[] colunas = {"Nome", "Idade", "Sexo", "Idioma"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registros Salvos"));
        add(scrollPane, BorderLayout.CENTER);

        // Eventos
        btnAdicionar.addActionListener(e -> adicionarRegistro());
        btnSalvarCsv.addActionListener(e -> gerarArquivoCSV());
    }

    private void adicionarRegistro() {
        try {
            String nome = txtNome.getText().trim();
            String idioma = txtIdioma.getText().trim();

            if (nome.isEmpty() || idioma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome e Idioma são campos obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idade = Integer.parseInt(txtIdade.getText().trim());
            String sexo = cbSexo.getSelectedItem().toString();

            Pessoa pessoa = new Pessoa(nome, idade, sexo, idioma);
            listaPessoas.add(pessoa);

            // Injeta diretamente o retorno do método obterDados()
            modeloTabela.addRow(pessoa.obterDados());

            // Limpeza dos campos
            txtNome.setText("");
            txtIdade.setText("");
            txtIdioma.setText("");
            txtNome.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um número inteiro válido para a idade.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarArquivoCSV() {
        if (listaPessoas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A lista está vazia. Adicione pessoas primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String caminhoArquivo = "cadastro_pessoas.csv";

        try {
            Arquivo.exportarParaCsv(listaPessoas, caminhoArquivo);
            JOptionPane.showMessageDialog(this, "Arquivo CSV gerado com sucesso em:\n" + caminhoArquivo, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}