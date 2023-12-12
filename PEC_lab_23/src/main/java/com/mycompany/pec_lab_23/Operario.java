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
    
    Operario(Banco banco, String idOperario) { 
        this.banco=banco;
       this.cajero=cajero;
        this.idOperario=idOperario;
    }
    public void run(){
        while (true){
            try{
              while (cajero.estaLleno()!=true && cajero.estaVacio()!=true) {
                    esperarCajero();
                    //comprueba si tiene que vaciar o rellenar el cajero
                }
              if(cajero.estaLleno()==true){
                  bolsa=cajero.vaciarCajero();
                  wait(2000);
                  banco.ingresarDinero(bolsa);
              }
              else if(cajero.estaVacio()==true){
                  bolsa=50000;
                  banco.extraerDinero(bolsa);
                  wait(3000);
                  cajero.rellenaCajero(bolsa);
              }
              bolsa=0;
            }catch(Exception e){}
        }
    }
    private void esperarCajero() throws InterruptedException {
        cajeroLleno.await();
        cajeroVacio.await();
    }
    
}
