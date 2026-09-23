/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import beans.Pessoa;
import conexao.Conexao;
import dao.PessoaDAO;
/**
 *
 * @author laboratorio
 */
public class Principal {
    public static void main(String[] args){
        Conexao c = new Conexao();
        c.getConexao();
        Pessoa p = new Pessoa();
        p.setNome("Vitor Bortoluzzi");
        p.setIdioma("Português");
        p.setSexo("M");
        PessoaDAO pdao = new PessoaDAO();
        pdao.inserir(p);
    }
}
