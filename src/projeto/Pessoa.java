/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto;

import java.util.List;

/**
 *
 * @author laboratorio
 */
public class Pessoa {
    private String nome;
    private int idade;
    private String sexo;
    private String idioma;

    public Pessoa(String nome, int idade, String sexo, String idioma) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.idioma = idioma;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getSexo() { return sexo; }
    public String getIdioma() { return idioma; }

    // Array preparado com todos os atributos incluindo o idioma
    public Object[] obterDados() {
        return new Object[] { nome, idade, sexo, idioma };
    }

    public String toCsvRow() {
        String nomeFormatado = nome.replace("\"", "\"\"");
        String idiomaFormatado = idioma.replace("\"", "\"\"");
        return String.format("\"%s\",%d,\"%s\",\"%s\"", nomeFormatado, idade, sexo, idiomaFormatado);
    }
}