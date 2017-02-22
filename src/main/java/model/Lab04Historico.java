/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;

/**
 *
 * @author davip
 */
public class Lab04Historico {
    private int numAge;
    private int numConta;
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int min;
    private int seg;
    private int codHist;
    private double valor;
    private Vector vetOperacoes;
    
    public Lab04Historico(int numAge, int numConta){
        this.numAge = numAge;
        this.numConta = numConta;
    }
    
    public boolean gravar(int codHist,double valor){
        FileWriter tArq1;
        PrintWriter tArq2;
        try{
            tArq1 = new FileWriter(numAge + "." + numConta + ".hist",true);
            tArq2 = new PrintWriter(tArq1);
            
            Date hoje = new Date();
            Calendar cal = new GregorianCalendar();
            cal.setTime(hoje);
            dia = cal.get(Calendar.DAY_OF_MONTH);
            mes = cal.get(Calendar.MONTH) + 1;
            ano = cal.get(Calendar.YEAR);
            hora = cal.get(Calendar.HOUR);
            min = cal.get(Calendar.MINUTE);
            seg = cal.get(Calendar.SECOND);
            
            tArq2.print(numAge + " ");
            tArq2.print(numConta + " ");
            tArq2.print(dia + " ");
            tArq2.print(mes + " ");
            tArq2.print(ano + " ");
            tArq2.print(hora + " ");
            tArq2.print(min + " ");
            tArq2.print(seg + " ");
            tArq2.print(codHist + " ");
            tArq2.println(valor);
            tArq2.close();
            return true;
        }catch(IOException tExcept){
            tExcept.printStackTrace();;
            return false;
        }
    }
    
    public void imprimir(){
        String[] s;
        NumberFormat formatter;
        StringBuffer sb;
        this.recuperarHistorico();
        for(int i=0;i<vetOperacoes.size();i++){
            s = vetOperacoes.get(i).toString().split(" ");
            //agencia
            formatter = new DecimalFormat("0000");
            sb = new StringBuffer();
            sb.append(formatter.format(numAge));
            sb.append(" ");
            System.out.print(sb);
            //conta
            formatter = new DecimalFormat("0000000");
            sb = new StringBuffer();
            sb.append(formatter.format(numConta));
            sb.append(" ");
            System.out.print(sb);
            //dia
            formatter = new DecimalFormat("00");
            sb = new StringBuffer();
            sb.append(formatter.format(Integer.valueOf(s[2])));
            sb.append("/");
            System.out.print(sb);
            //mes
            formatter = new DecimalFormat("00");
            sb = new StringBuffer();
            sb.append(formatter.format(Integer.valueOf(s[3])));
            sb.append("/");
            System.out.print(sb);
            //ano
            formatter = new DecimalFormat("0000");
            sb = new StringBuffer();
            sb.append(formatter.format(Integer.valueOf(s[4])));
            sb.append(" - ");
            System.out.print(sb);
            //hora
            formatter = new DecimalFormat("00");
            sb = new StringBuffer();
            sb.append(formatter.format(Integer.valueOf(s[5])));
            sb.append(":");
            System.out.print(sb);
            //minutos
            formatter = new DecimalFormat("00");
            sb = new StringBuffer();
            sb.append(formatter.format(Integer.valueOf(s[6])));
            sb.append(":");
            System.out.print(sb);
            //segundos
            formatter = new DecimalFormat("00");
            sb = new StringBuffer();
            sb.append(formatter.format(Integer.valueOf(s[7])));
            sb.append(" - ");
            System.out.print(sb);
            //saque/deposito
            switch(s[8]){
                case "1":
                    System.out.print("Saque ");
                    break;
                case "2":
                    System.out.print("Deposito ");
                    break;
            }
            //valor
            formatter = DecimalFormat.getCurrencyInstance(new Locale("pt","BR"));
            formatter.setMinimumFractionDigits(2);
            System.out.println(formatter.format(Double.valueOf(s[9])));
        }
        System.out.println("---------------------------------------------");
    }
    
    public void recuperarHistorico(){
        FileReader tArq1;
        BufferedReader tArq2;
        String tLinha;
        vetOperacoes = new Vector(0);
        try{
            tArq1 = new FileReader(numAge + "." + numConta + ".hist");
            tArq2 = new BufferedReader(tArq1);
            while(true){
                tLinha = tArq2.readLine();
                if(tLinha == null)
                    break;
                vetOperacoes.add(tLinha);
            }
            tArq2.close();
        }catch(FileNotFoundException e)
        {
            System.out.println("\n Conta sem movimento \n\n");
        }catch(IOException tExcept)
        {
            tExcept.printStackTrace();
        }
    }
}
