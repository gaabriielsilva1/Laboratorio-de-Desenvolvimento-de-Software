/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto;

import java.util.*;

/**
 *
 * @author laboratorio
 */
public class Locadora {
    public static void main (String[] args) {
        
        Carro c = new Carro();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite a marca do carro: ");
        c.marca = sc.nextLine();
        System.out.println("Digite o modelo do carro: ");
        c.modelo = sc.nextLine();
        
        c.exibirDados();
        
        sc.close();
    }
}
