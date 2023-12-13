/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pec_lab_23;

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
    private int saldo;
    
    public Banco(int dineroBanco) {
        
        this.dineroBanco = dineroBanco;
        this.cajero=cajero;
        this.operario= operario;
        this.persona=persona;
        
    }
    public synchronized void ingresarDinero(int cantidad) {
        dineroBanco += cantidad;
        notify(); 
    }
    public synchronized void extraerDinero(int cantidad) {
        while (dineroBanco< cantidad) {
            try {
                System.out.println("No hay suficiente dinero en el banco central. Esperando...");
                wait(); // Esperar si no hay suficiente dinero en el banco central
            } catch (InterruptedException e) {}
        }
        dineroBanco-= cantidad;
        notify(); 
    }
    public synchronized void ingresarCajero(Persona persona, Cajero cajero, int cantidad) {
        cajero.ingresarDinero(persona, cantidad);
        getSaldo(cajero);
        System.out.println(cantidad+" "+cajero.saldoCajero());
        operacion=(persona.getIdPersona()+ "- I - " + cantidad);
        notify();
    }
    public synchronized void extraerCajero(Persona persona, Cajero cajero, int cantidad) {
        cajero.extraerDinero(persona, cantidad);
        getSaldo(cajero);
        operacion=(persona.getIdPersona()+ "- E - " + cantidad);
        
        notify();
    }
    public void getSaldo(Cajero cajero) { //Obtine los datos de la operacion de la persona
        switch (cajero.idCajero()) {
            case 1:
                saldo=cajero.saldoCajero();
                setIdCajero1();
                estadoCajero1();
                break;
            case 2:
                saldo=cajero.saldoCajero();
                setIdCajero2();
                estadoCajero2();
                break;
            case 3:
                saldo=cajero.saldoCajero();
                setIdCajero3();
                estadoCajero3();
                break;
            case 4:
                saldo=cajero.saldoCajero();
                setIdCajero4();
                estadoCajero4();
                break;
            default:
                throw new AssertionError();
        }
    }

    public synchronized String estadoCajero1(){
        return operacion;
    }
    public synchronized String estadoCajero2(){
        return operacion;
    }
    public synchronized String estadoCajero3(){
        return "ha ingresa 3";
    }
    public synchronized String estadoCajero4(){
        return "ha ingresa 4";
    }

public String setIdCajero1() {
        return Integer. toString(saldo);
    }

    public String setIdCajero2() {
        return Integer. toString(saldo);
    }
public String setIdCajero3() {
        return Integer. toString(saldo);
    }
public String setIdCajero4() {
        return Integer. toString(saldo);
    }

}