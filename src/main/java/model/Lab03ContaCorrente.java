/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author davip
 */
public class Lab03ContaCorrente {
    protected int numAge;
    protected int numConta;
    protected String nome;
    protected double saldo;
    
    
    public Lab03ContaCorrente(){
        
    }
    
    public Lab03ContaCorrente(int numAge, int numConta){
        this.numAge = numAge;
        this.numConta = numConta;
        recuperar();
    }
    
    public Lab03ContaCorrente(int numAge, int numConta, String nome, double saldo){
        this.numAge = numAge;
        this.numConta = numConta;
        this.nome = nome;
        this.saldo = saldo;
    }
    
    public boolean gravar(){
        FileWriter tArq1;
        PrintWriter tArq2;
        try{
            tArq1 = new FileWriter(getNumAge()+ "." + getNumConta()+ ".dat");
            tArq2 = new PrintWriter(tArq1);
            tArq2.println(getNumAge());
            tArq2.println(getNumConta());
            tArq2.println(getNome());
            tArq2.println(getSaldo());
            tArq2.close();
            return true;
        }
        catch(IOException tExcept)
        {
            tExcept.printStackTrace();
            return false;
        }
    }
    
    protected void recuperar(){
        FileReader tArq1;
        BufferedReader tArq2;
        int tQtde=4;
        try{
            tArq1 = new FileReader (getNumAge() + "." + getNumConta() + ".dat");
            tArq2 = new BufferedReader (tArq1);
            String [] tLinha = new String [tQtde];
            for(int i=0;i<tQtde;i++){
                tLinha[i] = tArq2.readLine();
            }
            tArq2.close();
            setNumAge(Integer.parseInt(tLinha[0]));
            setNumConta(Integer.parseInt(tLinha[1]));
            setNome(tLinha[2]);
            setSaldo(Double.parseDouble(tLinha[3]));
        }
        catch(IOException tExcept)
        {
            tExcept.printStackTrace();
        }
    }

    public int getNumAge() {
        return numAge;
    }

    public void setNumAge(int numAge) {
        if(numAge>9999 || numAge<1){
            System.out.println("Erro nos parâmetros");
            return;
        }
        this.numAge = numAge;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        if(numConta>9999999 || numConta<1){
            System.out.println("Erro nos parâmetros");
            return;
        }
        this.numConta = numConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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
        System.out.println("---------------------------------------------");
        System.out.println("              Situação da Conta              ");
        System.out.println("---------------------------------------------");
        System.out.println("Agencia : " + numAge);
        System.out.println("Conta   : " + numConta);
        System.out.println("Nome    : " + nome);
        NumberFormat formatter;
        formatter = DecimalFormat.getCurrencyInstance(new Locale("pt","BR"));
        formatter.setMinimumFractionDigits(2);
        System.out.println("Saldo   : " + formatter.format(saldo));
        System.out.println("---------------------------------------------");
    }
    
    public boolean removerArquivo(){
        File tArq1;
        tArq1 = new File(numAge + "." + numConta + ".dat");
        tArq1.delete();
        tArq1 = new File(numAge + "." + numConta + ".hist");
        tArq1.delete();
        return true;
    }
}
