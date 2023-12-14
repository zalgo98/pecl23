/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pec_lab_23;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author gonzalo
 */
public class Persona extends Thread {

    private static Random random = new Random();
    private Cajero cajero;
    private String idPersona;
    private Banco banco;
    private boolean operacionCompletada; // Flag
    private Lock lock = new ReentrantLock();
    private Condition esperaCajero = lock.newCondition();
    private String op;
    private boolean pausa=false;
    List<Persona> listaPersonas= new ArrayList<>();
    public Persona(String idPersona, Cajero cajero, Banco banco) {
        this.cajero = cajero;
        this.idPersona = idPersona;
        this.operacionCompletada = false;
        this.banco = banco;
        
    }

    public void run() {
        listaPersonas.add(this);
        System.out.println(this);
        try {
            lock.lock();
            while (!operacionCompletada && pausa) {//&& banco.siguientes().size()==4
                int operacion = random.nextInt(2); // 0 para ingresar, 1 para extraer
                int cantidad = (random.nextInt(6) + 5) * 1000; // Importe aleatorio entre 5000 y 10000

                if (operacion == 0) {
                    Thread.sleep(random.nextInt(2000) + 2000);// Tiempo de espera aleatorio entre 2 y 4 segundos
                    if (100000 <= cajero.saldoCajero() + cantidad) {//Comprueba si al ingresar el dinero nos vamos a pasar
                        int cant=cantidad+cajero.saldoCajero()-100000;
                        banco.ingresarCajero(this, cajero,cant );//Deja el saldo al maximo
                        cantidad-=cant;
                        cajero.setLleno(true);
                    }
                    while (cajero.estaLleno()== true) {
                        esperaCajero.await();
                    }
                    // Persona ingresa dinero en cajero
                    banco.ingresarCajero(this, cajero, cantidad);
                    
                    
                } else {
                    Thread.sleep(random.nextInt(2000) + 2500);// Tiempo de espera aleatorio entre 2,5 y 4,5 segundos
                    if (0 >= cajero.saldoCajero() - cantidad) {//comprueba si al retirar el dinero nos vamos a pasar
                        int cant=cantidad-cajero.saldoCajero();
                        banco.extraerCajero(this, cajero,cant);//Deja el saldo a cero
                        cantidad-=cant;
                        cajero.setVacio(true);
                        
                    }
                    while (cajero.estaVacio() == true) {
                        esperaCajero.await();
                    }
                    banco.extraerCajero(this, cajero, cantidad); // Persona extrae dinero del cajero
                    
                    

                }

                operacionCompletada = true;
                
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
public String getIdPersona(){
    return idPersona;
}
public void pausarHilo() {
        pausa = true;
    }
public synchronized void reanudarHilo() {
        pausa = false;
        notify(); // Notificar al hilo que puede continuar su ejecución
    }
}
