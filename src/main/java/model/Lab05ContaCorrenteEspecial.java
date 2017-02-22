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
public class Lab05ContaCorrenteEspecial extends Lab03ContaCorrente {
    private double limite;
    
    public Lab05ContaCorrenteEspecial(int numAge, int numConta, String nome, double saldo, double limite){
        super(numAge,numConta,nome,saldo);
        this.limite = limite;
    }
    
    public Lab05ContaCorrenteEspecial(int numAge, int numConta){
        super(numAge,numConta);
    }
    
    @Override
    public int sacar(double saque){
        if(saque>(saldo+limite))
            return 0;
        else{
            saldo-=saque;
            return 1;
        }
    }
    
    @Override
    public boolean removerArquivo(){
        super.removerArquivo();
        File tArq1;
        tArq1 = new File(getNumAge() + "." + getNumConta() + ".esp");
        tArq1.delete();
        return true;
    }
    
    @Override
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
        System.out.println("Limite  : " + formatter.format(limite));
        System.out.println("---------------------------------------------");
    }
    
    @Override
    public boolean gravar(){
        super.gravar();
        FileWriter tArq1;
        PrintWriter tArq2;
        try{
            tArq1 = new FileWriter(getNumAge()+ "." + getNumConta()+ ".esp");
            tArq2 = new PrintWriter(tArq1);
            tArq2.println(getLimite());
            tArq2.close();
            return true;
        }
        catch(IOException tExcept)
        {
            tExcept.printStackTrace();
            return false;
        }
    }
    
    @Override
    protected void recuperar(){
        super.recuperar();
        FileReader tArq1;
        BufferedReader tArq2;
        try{
            tArq1 = new FileReader (getNumAge() + "." + getNumConta() + ".esp");
            tArq2 = new BufferedReader (tArq1);
            setLimite(Double.parseDouble(tArq2.readLine()));
            tArq2.close();
        }
        catch(IOException tExcept)
        {
            tExcept.printStackTrace();
        }
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    
    
}
