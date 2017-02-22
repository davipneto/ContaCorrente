/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davip
 */
public class Lab01Sistema {
    
    public static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        int op;
        do{
            System.out.println("1 - Cadastramento");
            System.out.println("2 - Saque");
            System.out.println("3 - Deposito");
            System.out.println("9 - Fim");
            System.out.print("\n" + "Opcao: ");
            op = s.nextInt();
            switch(op){
                case 1:
                    execCadastramento();
                    break;
                case 2:
                    execSaque();
                    break;
                case 3:
                    execDeposito();
                    break;
            }
        }while(op!=9);
        
    }
    public static void execCadastramento(){
        int numAgencia, numConta;
        String cliente;
        double saldo;
        
        System.out.print("Numero da Agencia : ");
        numAgencia = s.nextInt();
        System.out.print("Numero da Conta   : ");
        numConta = s.nextInt();
        s.nextLine();
        System.out.print("Nome do Cliente   : ");
        cliente = s.nextLine();
        System.out.print("Saldo             : ");
        saldo = s.nextDouble();
        s.nextLine();
        System.out.print("\n" + "Confirma cadastramento (S/N) : ");
        try {
            if((char)System.in.read() == 's'){
                System.out.println("Cadastramento realizado");
                //cadastrar();
            }
            else
                System.out.println("Cadastramento cancelado");
        } catch (IOException ex) {
            Logger.getLogger(Lab01Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.nextLine();
    }
    
    public static void execSaque(){
        int numAgencia, numConta;
        double valor;
        
        System.out.print("Numero da Agencia : ");
        numAgencia = s.nextInt();
        System.out.print("Numero da Conta   : ");
        numConta = s.nextInt();
        System.out.print("Valor do Saque    : ");
        valor = s.nextDouble();
        s.nextLine();
        System.out.print("\n" + "Confirma saque (S/N) : ");
        try {
            if((char)System.in.read() == 's'){
                System.out.println("Saque realizado");
                //sacar();
            }
            else
                System.out.println("Saque cancelado");
        } catch (IOException ex) {
            Logger.getLogger(Lab01Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.nextLine();
    }

    private static void execDeposito() {
        int numAgencia, numConta;
        double valor;
        
        System.out.print("Numero da Agencia : ");
        numAgencia = s.nextInt();
        System.out.print("Numero da Conta   : ");
        numConta = s.nextInt();
        System.out.print("Valor do Deposito : ");
        valor = s.nextDouble();
        s.nextLine();
        System.out.print("\n" + "Confirma deposito (S/N) : ");
        try {
            if((char)System.in.read() == 's'){
                System.out.println("Deposito realizado");
                //depositar();
            }
            else
                System.out.println("Deposito cancelado");
        } catch (IOException ex) {
            Logger.getLogger(Lab01Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.nextLine();
    }
}
