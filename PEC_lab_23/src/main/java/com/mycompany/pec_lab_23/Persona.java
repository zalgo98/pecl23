/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pec_lab_23;

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
    public Persona(String idPersona, Cajero cajero, Banco banco) {
        this.cajero = cajero;
        this.idPersona = idPersona;
        this.operacionCompletada = false;
        this.banco=banco;
    }

    public void run() {
        try {
            lock.lock();
            while (!operacionCompletada) {
                int operacion = random.nextInt(2); // 0 para ingresar, 1 para extraer
                int cantidad = (random.nextInt(6) + 5) * 1000; // Importe aleatorio entre 5000 y 10000
                
                if (operacion == 0) {
                    Thread.sleep(random.nextInt(2000) + 2000);// Tiempo de espera aleatorio entre 2 y 4 segundos
                     // Persona ingresa dinero en cajero
                    banco.ingresarCajero(this, cajero, cantidad);
                    op=idPersona+"-I+ "+cantidad; 
                    getIdPersona(op);
                } else {
                    Thread.sleep(random.nextInt(2000) + 2500);// Tiempo de espera aleatorio entre 2,5 y 4,5 segundos
                    banco.extraerCajero(this, cajero, cantidad); // Persona extrae dinero del cajero
                    op=idPersona+"-E- "+cantidad;
                    getIdPersona(op);
                }

                operacionCompletada = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void getIdPersona(String op0){ //Obtine los datos de la operacion de la persona
        switch (cajero.idCajero()) {
            case 1:
                setIdPersona1();
                break;
            case 2:
                setIdPersona2();
                break;
            case 3:
                setIdPersona3();
                break;
            case 4:
                setIdPersona4();
                break;    
            default:
                throw new AssertionError();
        }
    }
    public String setIdPersona1(){
        return op;
    }
    public String setIdPersona2(){
        return op;
    }
    public String setIdPersona3(){
        return op;
    }
    public String setIdPersona4(){
        return op;
    }
}