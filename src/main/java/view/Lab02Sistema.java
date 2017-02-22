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
import model.Lab02ContaCorrente;

/**
 *
 * @author davip
 */
public class Lab02Sistema {
    private static Lab02ContaCorrente cc = new Lab02ContaCorrente();
    private static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        int op;
        do{
            System.out.println("1 - Cadastramento");
            System.out.println("2 - Saque");
            System.out.println("3 - Deposito");
            System.out.println("4 - Imprimir valores da Conta");
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
                case 4:
                    execImprimir();
                    break;
            }
        }while(op!=9);
        
    }
    public static void execCadastramento(){
        int numAgencia, numConta;
        String cliente;
        double saldo;
        
        do{
            System.out.print("Numero da Agencia : ");
            numAgencia = s.nextInt();
        }while(numAgencia<0); //verificando valor valido para a agencia, deve ser um num não negativo
        do{
            System.out.print("Numero da Conta   : ");
            numConta = s.nextInt();
        }while(numConta<0); //verificando valor valido para a conta, deve ser um num não negativo
        s.nextLine();
        System.out.print("Nome do Cliente   : ");
        cliente = s.nextLine();
        do{
            System.out.print("Saldo             : ");
            saldo = s.nextDouble();
        }while(saldo<0.0); //saldo não pode ser negativo
        s.nextLine();
        System.out.print("\n" + "Confirma cadastramento (S/N) : ");
        try {
            if((char)System.in.read() == 's'){
                System.out.println("Cadastramento realizado");
                cc.numAge = numAgencia;
                cc.numConta = numConta;
                cc.nome = cliente;
                cc.saldo = saldo;
            }
            else
                System.out.println("Cadastramento cancelado");
        } catch (IOException ex) {
            Logger.getLogger(Lab02Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.nextLine();
    }
    
    public static void execSaque(){
        int numAgencia, numConta;
        double valor;
        
        do{
            System.out.print("Numero da Agencia : ");
            numAgencia = s.nextInt();
        }while(numAgencia<0); //verificando valor valido para a agencia, deve ser um num não negativo
        do{
            System.out.print("Numero da Conta   : ");
            numConta = s.nextInt();
        }while(numConta<0); //verificando valor valido para a conta, deve ser um num não negativo
        do{
            System.out.print("Valor do Saque    : ");
            valor = s.nextDouble();
        }while(valor<=0.0); //o valor do saque deve ser um valor positivo
        s.nextLine();
        System.out.print("\n" + "Confirma saque (S/N) : ");
        try {
            if((char)System.in.read() == 's'){
                cc.numAge=numAgencia;
                cc.numConta=numConta;
                if(cc.sacar(valor)==0)
                    System.out.println("Saldo Insuficiente");
                else
                    System.out.println("Saque realizado");
            }
            else
                System.out.println("Saque cancelado");
        } catch (IOException ex) {
            Logger.getLogger(Lab02Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.nextLine();
    }

    private static void execDeposito() {
        int numAgencia, numConta;
        double valor;
        
        do{
            System.out.print("Numero da Agencia : ");
            numAgencia = s.nextInt();
        }while(numAgencia<0); //verificando valor valido para a agencia, deve ser um num não negativo
        do{
            System.out.print("Numero da Conta   : ");
            numConta = s.nextInt();
        }while(numConta<0); //verificando valor valido para a conta, deve ser um num não negativo
        do{
            System.out.print("Valor do Deposito : ");
            valor = s.nextDouble();
        }while(valor<0.0); //verificando valor valido para a valor, deve ser positivo
        s.nextLine();
        System.out.print("\n" + "Confirma deposito (S/N) : ");
        try {
            if((char)System.in.read() == 's'){
                cc.numAge=numAgencia;
                cc.numConta=numConta;
                cc.depositar(valor);
                System.out.println("Deposito realizado");
            }
            else
                System.out.println("Deposito cancelado");
        } catch (IOException ex) {
            Logger.getLogger(Lab02Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.nextLine();
    }
    
    public static void execImprimir(){
        cc.imprimir();
    }
}
