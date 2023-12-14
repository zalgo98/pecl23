/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pec_lab_23;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gonzalo
 */
class Banco {

    private int dineroBanco;
    private Cajero cajero;
    private Persona persona;
    private Operario operario;
    private String operacion;
    private int saldo1;
    private int saldo2;
    private int saldo3;
    private int saldo4;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    
    private boolean pausa = false;

    public Banco(int dineroBanco) {

        this.dineroBanco = dineroBanco;
        this.cajero = cajero;
        this.operario = operario;
        this.persona = persona;

    }

    public synchronized void ingresarDinero(int cantidad) {
        dineroBanco += cantidad;
        notify();
    }

    public synchronized void extraerDinero(int cantidad) {
        while (dineroBanco < cantidad) {
            try {
                System.out.println("No hay suficiente dinero en el banco central. Esperando...");
                wait(); // Esperar si no hay suficiente dinero en el banco central
            } catch (InterruptedException e) {
            }
        }
        dineroBanco -= cantidad;
        notify();
    }

    public synchronized void ingresarCajero(Persona persona, Cajero cajero, int cantidad) {
        
            
            cajero.ingresarDinero(persona, cantidad);
            getSaldo(cajero);
            operacion = (persona.getIdPersona() + "- I - " + cantidad);
            notify();
        
    }

    public synchronized void extraerCajero(Persona persona, Cajero cajero, int cantidad) {
        
            
            cajero.extraerDinero(persona, cantidad);
            getSaldo(cajero);
            operacion = (persona.getIdPersona() + "- E - " + cantidad);

            notify();
        
    }

    public void getSaldo(Cajero cajero) { //Obtiene los datos de la operacion de la persona

        switch (cajero.idCajero()) {
            case 1:
                saldo1 = cajero.saldoCajero();//Guarda el saldo del cajero
                op1 = operacion;//Guarda la ultima 
                setIdCajero1();//imprime el saldo del cajero 
                estadoCajero1();//imprime la ultima operacion realizada

                break;
            case 2:
                saldo2 = cajero.saldoCajero();
                op2 = operacion;
                setIdCajero2();
                estadoCajero2();

                break;
            case 3:
                saldo3 = cajero.saldoCajero();
                op3 = operacion;
                setIdCajero3();
                estadoCajero3();

                break;
            case 4:
                saldo4 = cajero.saldoCajero();
                op4 = operacion;
                setIdCajero4();
                estadoCajero4();

                break;
            default:
                throw new AssertionError();
        }
    }

    public synchronized String estadoCajero1() {
        return op1;
    }

    public synchronized String estadoCajero2() {
        return op2;
    }

    public synchronized String estadoCajero3() {
        return op3;
    }

    public synchronized String estadoCajero4() {
        return op4;
    }

    public String setIdCajero1() {
        return Integer.toString(saldo1);
    }

    public String setIdCajero2() {
        return Integer.toString(saldo2);
    }

    public String setIdCajero3() {
        return Integer.toString(saldo3);
    }

    public String setIdCajero4() {
        return Integer.toString(saldo4);
    }

    public void pausarHilo() {
        pausa = true;
    }

    public synchronized void reanudarHilo() {
        pausa = false;
        notify(); // Notificar al hilo que puede continuar su ejecución
    }

}
