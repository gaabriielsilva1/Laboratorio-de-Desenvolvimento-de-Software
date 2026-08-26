/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto;

/**
 *
 * @author laboratorio
 */
public class Carro {
    public String marca;
    public String modelo;
    
    public void alugar(){
        System.out.println("Carro: " + marca + "\nModelo: " + modelo + " \nAlugado!");
    }
    
    public void devolver(){
        System.out.println("Carro: " + marca + "\nModelo: " + modelo + " \nDevolvido!");
    }
    
    public void exibirDados(){
        System.out.println("Carro: " + marca + "\nModelo: " + modelo);
    }
    
}
