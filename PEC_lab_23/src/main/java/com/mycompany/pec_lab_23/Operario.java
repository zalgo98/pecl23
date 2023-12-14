/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pec_lab_23;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author gonzalo
 */
class Operario extends Thread {
    private Banco banco;
    private Cajero cajero;
    private String idOperario;
    private Lock lock = new ReentrantLock();
    private Condition cajeroLleno = lock.newCondition();
    private Condition cajeroVacio = lock.newCondition();
    private int bolsa;
    private boolean pausa=false;
    Operario(Banco banco, String idOperario) { 
        this.banco=banco;
       this.cajero=cajero;
        this.idOperario=idOperario;
    }
    public void run(){
        while (pausa==false){
            try{
              while (cajero.estaLleno()!=true && cajero.estaVacio()!=true) {
                    esperarCajero();
                    //comprueba si tiene que vaciar o rellenar el cajero
                }
              if(cajero.estaLleno()==true){
                  vaciarCajero(cajero);
                  wait(2000);
                  banco.ingresarDinero(bolsa, this);
              }
              else if(cajero.estaVacio()==true){
                  bolsa=50000;
                  banco.extraerDinero(bolsa, this);
                  wait(3000);
                  rellenaCajero(bolsa, cajero);
              }
              bolsa=0;
            }catch(Exception e){}
        }
    }
    private void esperarCajero() throws InterruptedException {
        cajeroLleno.await();
        cajeroVacio.await();
    }
    public void vaciarCajero(Cajero cajero){//Vacia los cajeros
        int retirada=cajero.saldoCajero() - 50000;
        cajero.setLleno(false);
        cajero.setSaldo(retirada);
    }
    public void rellenaCajero(int dinero, Cajero cajero){//rellena los cajeros
        cajero.setVacio(false);
        cajero.setSaldo(dinero);
    }
    public String setID(){
        return idOperario;
    }
    public int idCajero(){
        return cajero.idCajero();
    }
   
    
}
