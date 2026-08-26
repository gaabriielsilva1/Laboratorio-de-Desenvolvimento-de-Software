/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto;

/**
 *
 * @author laboratorio
 */
public class Disciplina {
    public static void main(String[] args) {
        
//        Professor p1 = new Professor();
//        Laboratorio l1 = new Laboratorio();
//        p1.nome = "Ricardo da Silva";
//        l1.local = "Sala 108";
//        
//        System.out.println("O nome do professor é: " + p1.nome);
//        System.out.println("O local da aula é: " + l1.local);
        Pessoa p1 = new Pessoa("Carlos", 23, 'M');
        System.out.println("Nome da pessoa: " + p1.nome);
        System.out.println("Idade da pessoa: " + p1.idade);
        System.out.println("Genero da pessoa: " + p1.genero);
        
        Livro l1 = new Livro("Guy", "Carlos Alberto", 2008);
        System.out.println("Titulo do Livro: " + l1.titulo);
        System.out.println("Nome do Autor: " + l1.autor);
        System.out.println("Ano de publicaçao: " + l1.ano);
        
       
        
    }
}
