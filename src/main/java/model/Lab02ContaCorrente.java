/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author davip
 */
public class Lab02ContaCorrente {
    public int numAge;
    public int numConta;
    public String nome;
    public double saldo;
    
    public Lab02ContaCorrente(){
        
    }
    
    public int sacar(double saque){
        if(saldo>=saque){
            saldo-=saque;
            return 1;
        }
        return 0;
    }
    
    public void depositar(double deposito){
        saldo+=deposito;
    }
    
    public void imprimir(){
        System.out.println("Numero da Agencia : " + numAge);
        System.out.println("Numero da Conta   : " + numConta);
        System.out.println("Nome do Cliente   : " + nome);
        System.out.println("Saldo             : " + saldo);
    }
}
