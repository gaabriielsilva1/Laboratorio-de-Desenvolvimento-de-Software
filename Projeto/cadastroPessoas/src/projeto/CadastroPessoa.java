package projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class CadastroPessoa extends JFrame {

    private final List<Pessoa> listaPessoas = new ArrayList<>();
    private DefaultTableModel modeloTabela;
    private JTable tbl_Pessoas;
    private int LinhaEdicao = -1;

    private JTextField txtNome;
    private JTextField txtIdade;
    
    private JRadioButton rdoFeminino;
    private JRadioButton rdoMasculino;
    private JRadioButton rdoOutro;
    private ButtonGroup bgSexo;
    
    private JComboBox<String> cmbIdioma; 
    private List<String> listaTodosIdiomas; 
    
    private JRadioButton rdoTecnologia;
    private JRadioButton rdoAstronomia;
    private JRadioButton rdoEsportes;
    private ButtonGroup bgInteresse;

    public CadastroPessoa() {
        setTitle("Cadastro e Exportação de Pessoas (CSV)");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        carregarIdiomasGlobais();
        inicializarComponentes();
    }

    private void carregarIdiomasGlobais() {
        Set<String> idiomasSet = new TreeSet<>(); 
        Locale[] locais = Locale.getAvailableLocales();
        
        for (Locale loc : locais) {
            String idiomaNome = loc.getDisplayLanguage(new Locale("pt", "BR"));
            if (!idiomaNome.isEmpty()) {
                idiomaNome = idiomaNome.substring(0, 1).toUpperCase() + idiomaNome.substring(1);
                idiomasSet.add(idiomaNome);
            }
        }
        listaTodosIdiomas = new ArrayList<>(idiomasSet);
    }

    private void inicializarComponentes() {
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new BoxLayout(painelFormulario, BoxLayout.Y_AXIS));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Linha 1 
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        linha1.add(new JLabel("Nome:"));
        txtNome = new JTextField(20);
        linha1.add(txtNome);

        linha1.add(new JLabel("Idade:"));
        txtIdade = new JTextField(5);
        linha1.add(txtIdade);
        painelFormulario.add(linha1);

        // Linha 2 
        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        linha2.add(new JLabel("Sexo:"));
        
        rdoFeminino = new JRadioButton("Feminino", true);
        rdoMasculino = new JRadioButton("Masculino");
        rdoOutro = new JRadioButton("Outro");
        
        bgSexo = new ButtonGroup();
        bgSexo.add(rdoFeminino);
        bgSexo.add(rdoMasculino);
        bgSexo.add(rdoOutro);
        
        JPanel panelSexo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSexo.add(rdoFeminino);
        panelSexo.add(rdoMasculino);
        panelSexo.add(rdoOutro);
        linha2.add(panelSexo);

        linha2.add(new JLabel("Idioma:"));
        
        cmbIdioma = new JComboBox<>(listaTodosIdiomas.toArray(new String[0]));
        cmbIdioma.setPreferredSize(new Dimension(150, 25));
        
        // 1º Torna o campo editável
        configurarAutocompletarIdioma();
        
        // 2º Força a remoção de qualquer seleção (deixando a caixa 100% vazia)
        cmbIdioma.setSelectedIndex(-1); 
        
        linha2.add(cmbIdioma);

        painelFormulario.add(linha2);

        // Linha 3
        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        linha3.add(new JLabel("Interesse:"));
        
        rdoTecnologia = new JRadioButton("Tecnologia", true);
        rdoAstronomia = new JRadioButton("Astronomia");
        rdoEsportes = new JRadioButton("Esportes");
        
        bgInteresse = new ButtonGroup();
        bgInteresse.add(rdoTecnologia);
        bgInteresse.add(rdoAstronomia);
        bgInteresse.add(rdoEsportes);
        
        JPanel panelInteresse = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelInteresse.add(rdoTecnologia);
        panelInteresse.add(rdoAstronomia);
        panelInteresse.add(rdoEsportes);
        linha3.add(panelInteresse);
        
        painelFormulario.add(linha3);
        
        add(painelFormulario, BorderLayout.NORTH);

        // --- PAINEL CENTRAL (Tabela) ---
        String[] colunas = {"Nome", "Idade", "Sexo", "Idioma", "Interesse"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tbl_Pessoas = new JTable(modeloTabela);
        
        Color corFundoTabela = new Color(255, 250, 205);
        tbl_Pessoas.setBackground(corFundoTabela);
        tbl_Pessoas.setOpaque(true);
        
        JScrollPane scrollPane = new JScrollPane(tbl_Pessoas);
        scrollPane.getViewport().setBackground(corFundoTabela);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registros Salvos"));
        add(scrollPane, BorderLayout.CENTER);

        // --- PAINEL INFERIOR (Divisão e Botões) ---
        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        
        JButton btnAdicionar = new JButton("Adicionar / Salvar"); 
        JButton btnEditar = new JButton("Editar"); 
        JButton btnExcluir = new JButton("Excluir Cadastro");
        
        JButton btnSalvarCsv = new JButton("Gerar CSV");
        btnSalvarCsv.setBackground(new Color(34, 139, 34));
        btnSalvarCsv.setForeground(Color.WHITE);
        
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnSalvarCsv);

        painelRodape.add(painelBotoes, BorderLayout.CENTER);
        add(painelRodape, BorderLayout.SOUTH);

        // Eventos
        btnAdicionar.addActionListener(e -> adicionarRegistro());
        btnEditar.addActionListener(e -> btnEditar(e)); 
        btnExcluir.addActionListener(e -> excluirRegistro());
        btnSalvarCsv.addActionListener(e -> gerarArquivoCSV());
    }

    private void configurarAutocompletarIdioma() {
        cmbIdioma.setEditable(true); 
        final JTextField editorDeTexto = (JTextField) cmbIdioma.getEditor().getEditorComponent();
        
        editorDeTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int tecla = e.getKeyCode();
                if (tecla == KeyEvent.VK_UP || tecla == KeyEvent.VK_DOWN || tecla == KeyEvent.VK_ENTER || tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_RIGHT) {
                    return;
                }

                SwingUtilities.invokeLater(() -> {
                    String digitado = editorDeTexto.getText();
                    
                    DefaultComboBoxModel<String> modeloFiltrado = new DefaultComboBoxModel<>();
                    for (String idioma : listaTodosIdiomas) {
                        if (idioma.toLowerCase().contains(digitado.toLowerCase())) {
                            modeloFiltrado.addElement(idioma);
                        }
                    }
                    
                    cmbIdioma.setModel(modeloFiltrado);
                    editorDeTexto.setText(digitado); 
                    
                    if (modeloFiltrado.getSize() > 0) {
                        cmbIdioma.showPopup();
                    } else {
                        cmbIdioma.hidePopup();
                    }
                });
            }
        });
    }

    private void adicionarRegistro() {
        try {
            String nome = txtNome.getText().trim();
            // Evita erro de NullPointer caso o usuário clique em adicionar sem digitar nada no idioma
            String idioma = "";
            if (cmbIdioma.getEditor().getItem() != null) {
                idioma = cmbIdioma.getEditor().getItem().toString().trim();
            }

            if (nome.isEmpty() || idioma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome e Idioma são campos obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idade = Integer.parseInt(txtIdade.getText().trim());
            
            char sexo = ' ';
            if (rdoFeminino.isSelected()) sexo = 'F';
            else if (rdoMasculino.isSelected()) sexo = 'M';
            else if (rdoOutro.isSelected()) sexo = 'O';

            String interesse = "";
            if (rdoTecnologia.isSelected()) interesse = "Tecnologia";
            else if (rdoAstronomia.isSelected()) interesse = "Astronomia";
            else if (rdoEsportes.isSelected()) interesse = "Esportes";

            if (LinhaEdicao >= 0) {
                Pessoa p = listaPessoas.get(LinhaEdicao);
                p.nome = nome;
                p.idade = idade;
                p.sexo = sexo;
                p.idioma = idioma;
                p.interesse = interesse;

                modeloTabela.setValueAt(nome, LinhaEdicao, 0);
                modeloTabela.setValueAt(idade, LinhaEdicao, 1);
                modeloTabela.setValueAt(String.valueOf(sexo), LinhaEdicao, 2);
                modeloTabela.setValueAt(idioma, LinhaEdicao, 3);
                modeloTabela.setValueAt(interesse, LinhaEdicao, 4);

                LinhaEdicao = -1; 
                JOptionPane.showMessageDialog(this, "Cadastro atualizado com sucesso!");
                
            } else {
                Pessoa pessoa = new Pessoa(nome, idade, sexo, idioma, interesse);
                listaPessoas.add(pessoa);
                modeloTabela.addRow(pessoa.obterDados());
            }

            // Limpeza
            txtNome.setText("");
            txtIdade.setText("");
            cmbIdioma.setModel(new DefaultComboBoxModel<>(listaTodosIdiomas.toArray(new String[0])));
            cmbIdioma.setSelectedIndex(-1); // Limpa a caixa novamente após o salvamento
            rdoFeminino.setSelected(true);
            rdoTecnologia.setSelected(true);
            txtNome.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um número inteiro válido para a idade.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnEditar(java.awt.event.ActionEvent e){
        int linha = tbl_Pessoas.getSelectedRow();

        if(linha == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para editar");
            return;
        }
        
        LinhaEdicao = linha;
        Pessoa p = listaPessoas.get(linha);
        
        txtNome.setText(p.nome);
        txtIdade.setText(String.valueOf(p.idade)); 

        if (p.sexo == 'M'){
            rdoMasculino.setSelected(true);
        } else if (p.sexo == 'F') {
            rdoFeminino.setSelected(true);
        } else {
            rdoOutro.setSelected(true);
        }

        cmbIdioma.setSelectedItem(p.idioma);
        
        if (p.interesse.equals("Tecnologia")) {
            rdoTecnologia.setSelected(true);
        } else if (p.interesse.equals("Astronomia")) {
            rdoAstronomia.setSelected(true);
        } else if (p.interesse.equals("Esportes")) {
            rdoEsportes.setSelected(true);
        }
    }

    private void excluirRegistro() {
        int linha = tbl_Pessoas.getSelectedRow();
        
        if(linha == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa na tabela.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta pessoa?", "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if (resposta == JOptionPane.YES_OPTION) {
            listaPessoas.remove(linha);
            modeloTabela.removeRow(linha);
            
            if (LinhaEdicao == linha) {
                LinhaEdicao = -1; 
            }
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