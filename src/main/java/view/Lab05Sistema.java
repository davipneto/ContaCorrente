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
import model.Lab03ContaCorrente;
import model.Lab04Historico;
import model.Lab05ContaCorrenteEspecial;

/**
 *
 * @author davip
 */
public class Lab05Sistema {
    private static Lab03ContaCorrente cc;
    private static Lab05ContaCorrenteEspecial ce;
    private static Lab04Historico hist;
    private static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        int op;
        do{
            System.out.println("1 - Cadastramento");
            System.out.println("2 - Saque");
            System.out.println("3 - Deposito");
            System.out.println("4 - Consulta");
            System.out.println("5 - Extrato");
            System.out.println("8 - Remover Conta Corrente");
            System.out.println("9 - Fim");
            System.out.print("\n" + "Entre com a opcao desejada: ");
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
                    execConsulta();
                    break;
                case 5:
                    execExtrato();
                    break;
                case 8:
                    execRemoverContaCorrente();
                    break;
            }
        }while(op!=9);
        
    }
    public static void execCadastramento(){
        int numAgencia, numConta;
        String cliente;
        double saldo,limite;
        
        System.out.print("Numero da Agencia : ");
        numAgencia = s.nextInt();
        System.out.print("Numero da Conta   : ");
        numConta = s.nextInt();
        s.nextLine();
        System.out.print("Nome do Cliente   : ");
        cliente = s.nextLine();
        System.out.print("Saldo             : ");
        saldo = s.nextDouble();
        if(numAgencia>=5000){
            System.out.print("Limite            : ");
            limite = s.nextDouble();
            s.nextLine();
            ce = new Lab05ContaCorrenteEspecial(numAgencia,numConta,cliente,saldo,limite);
            System.out.print("\n" + "Confirma cadastramento (S/N) : ");
            try {
                if((char)System.in.read() == 's'){
                    ce.gravar();
                    System.out.println("Cadastramento realizado");
                }
                else
                    System.out.println("Cadastramento cancelado");
            } catch (IOException ex) {
                Logger.getLogger(Lab05Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            s.nextLine();
            cc = new Lab03ContaCorrente(numAgencia,numConta,cliente,saldo);
            System.out.print("\n" + "Confirma cadastramento (S/N) : ");
            try {
                if((char)System.in.read() == 's'){
                    cc.gravar();
                    System.out.println("Cadastramento realizado");
                }
                else
                    System.out.println("Cadastramento cancelado");
            } catch (IOException ex) {
                Logger.getLogger(Lab05Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
            s.nextLine();
        }
    }
    
    public static void execSaque(){
        int numAgencia, numConta;
        double valor;
        
        System.out.print("Numero da Agencia : ");
        numAgencia = s.nextInt();
        System.out.print("Numero da Conta   : ");
        numConta = s.nextInt();
        do{
            System.out.print("Valor do Saque    : ");
            valor = s.nextDouble();
        }while(valor<=0.0); //o valor do saque deve ser um valor positivo
        s.nextLine();
        System.out.print("\n" + "Confirma saque (S/N) : ");
        if(numAgencia>=5000){
            try {
                if((char)System.in.read() == 's'){
                    ce = new Lab05ContaCorrenteEspecial(numAgencia,numConta);
                    if(ce.sacar(valor)==0)
                        System.out.println("Saldo Insuficiente");
                    else{
                        ce.gravar();
                        System.out.println("Saque realizado");
                        hist = new Lab04Historico(numAgencia,numConta);
                        hist.gravar(1, valor);
                    }
                }
                else
                    System.out.println("Saque cancelado");
            } catch (IOException ex) {
                Logger.getLogger(Lab05Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                if((char)System.in.read() == 's'){
                    cc = new Lab03ContaCorrente(numAgencia,numConta);
                    if(cc.sacar(valor)==0)
                        System.out.println("Saldo Insuficiente");
                    else{
                        cc.gravar();
                        System.out.println("Saque realizado");
                        hist = new Lab04Historico(numAgencia,numConta);
                        hist.gravar(1, valor);
                    }
                }
                else
                    System.out.println("Saque cancelado");
            } catch (IOException ex) {
                Logger.getLogger(Lab05Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        do{
            System.out.print("Valor do Deposito : ");
            valor = s.nextDouble();
        }while(valor<=0.0); //verificando valor valido para a valor, deve ser positivo
        s.nextLine();
        System.out.print("\n" + "Confirma deposito (S/N) : ");
        try {
            if((char)System.in.read() == 's'){
                cc = new Lab03ContaCorrente(numAgencia,numConta);
                cc.depositar(valor);
                cc.gravar();
                System.out.println("Deposito realizado");
                hist = new Lab04Historico(numAgencia,numConta);
                hist.gravar(2, valor);
            }
            else
                System.out.println("Deposito cancelado");
        } catch (IOException ex) {
            Logger.getLogger(Lab05Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.nextLine();
    }
    
    public static void execConsulta(){
        int numAgencia,numConta;
        System.out.print("Agencia : ");
        numAgencia = s.nextInt();
        System.out.print("Conta   : ");
        numConta = s.nextInt();
        System.out.println("\n" + "Confirma consulta (S/N): ");
        try {
            if((char)System.in.read() == 's'){
                if(numAgencia>=5000){
                    ce = new Lab05ContaCorrenteEspecial(numAgencia,numConta);
                    ce.imprimir();
                }
                else{
                    cc = new Lab03ContaCorrente(numAgencia,numConta);
                    cc.imprimir();
                }
            }
            else
                System.out.println("Consulta cancelada");
        } catch (IOException ex) {
            Logger.getLogger(Lab05Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.nextLine();
    }
    
    public static void execExtrato(){
        int numAgencia,numConta;
        System.out.print("Agencia : ");
        numAgencia = s.nextInt();
        System.out.print("Conta   : ");
        numConta = s.nextInt();
        System.out.println("\n" + "Confirma extrato (S/N): ");
        try {
            if((char)System.in.read() == 's'){
                if(numAgencia>=5000){
                    ce = new Lab05ContaCorrenteEspecial(numAgencia,numConta);
                    ce.imprimir();
                }
                else{
                    cc = new Lab03ContaCorrente(numAgencia,numConta);
                    cc.imprimir();
                }
                hist = new Lab04Historico(numAgencia,numConta);
                hist.imprimir();
            }
            else
                System.out.println("Extrato cancelado");
        } catch (IOException ex) {
            Logger.getLogger(Lab05Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.nextLine();
    }
    
    public static void execRemoverContaCorrente(){
        int numAgencia,numConta;
        System.out.print("Agencia : ");
        numAgencia = s.nextInt();
        System.out.print("Conta   : ");
        numConta = s.nextInt();
        System.out.println("\n" + "Confirma remocao (S/N): ");
        try {
            if((char)System.in.read() == 's'){
                if(numAgencia>=5000){
                    ce = new Lab05ContaCorrenteEspecial(numAgencia,numConta);
                    ce.removerArquivo();
                }
                else{
                    cc = new Lab03ContaCorrente(numAgencia,numConta);
                    cc.removerArquivo();
                }
                System.out.println("Conta removida");
            }
            else
                System.out.println("Remocao cancelada");
        } catch (IOException ex) {
            Logger.getLogger(Lab05Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.nextLine();
    }
}
