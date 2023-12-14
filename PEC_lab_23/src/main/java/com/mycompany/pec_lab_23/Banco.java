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
    private String trabajador;
    private int saldo1;
    private int saldo2;
    private int saldo3;
    private int saldo4;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String op5;
    private String op6;
    List<String> listaOperarios;
    private boolean pausa = false;

    public Banco(int dineroBanco) {

        this.dineroBanco = dineroBanco;
        this.cajero = cajero;
        this.operario = operario;
        this.persona = persona;
        this.listaOperarios=new ArrayList<>();

    }

    public synchronized void ingresarDinero(int cantidad, Operario operario) {
        trabajador=operario.setID()+ "-C"+ operario.idCajero() +" + "+ cantidad; 
        dineroBanco += cantidad;
        listaOperarios.add(trabajador);
        notify();
    }

    public synchronized void extraerDinero(int cantidad, Operario operario) {
        while (dineroBanco < cantidad) {
            try {
                
                wait(); // Esperar si no hay suficiente dinero en el banco central
            } catch (InterruptedException e) {
            }
        }
        trabajador=operario.setID()+ "-C"+ operario.idCajero() +" - "+ cantidad; 
        listaOperarios.add(trabajador);
        dineroBanco -= cantidad;
        notify();
    }

    public synchronized void ingresarCajero(Persona persona, Cajero cajero, int cantidad) {
       
        if (100000 < cajero.saldoCajero() + cantidad) {//Comprueba si al ingresar el dinero nos vamos a pasar
                        int cant=cantidad+cajero.saldoCajero()-100000;
                        ingresarCajero(persona, cajero,cant );//Deja el saldo al maximo
                        cantidad-=cant;
                        cajero.setLleno(true);
                        operario.vaciarCajero();
                    }
                    
        operacion = (persona.getIdPersona() + "- I - " + cantidad);
        cajero.ingresarDinero(persona, cantidad);
        
        getSaldo(cajero);
        notify();

    }

    public synchronized void extraerCajero(Persona persona, Cajero cajero, int cantidad) {
        if (0 >= cajero.saldoCajero() - cantidad) {//comprueba si al retirar el dinero nos vamos a pasar
                        int cant=cantidad-cajero.saldoCajero();
                        extraerCajero(persona, cajero,cant);//Deja el saldo a cero
                        cantidad-=cant;
                        cajero.setVacio(true);
                        operario.rellenaCajero(cantidad);
                    }
        operacion = (persona.getIdPersona() + "- E - " + cantidad);
        cajero.extraerDinero(persona, cantidad);
        
        getSaldo(cajero);
        notify();

    }
    public void getOperario(Operario operario){
        if(operario.setID()=="operario 1"){
            op5=trabajador;
        }
        else{
            op6= trabajador;
        }
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

    public String estadoCajero1() {
        return op1;
    }

    public String estadoCajero2() {
        return op2;
    }

    public String estadoCajero3() {
        return op3;
    }

    public String estadoCajero4() {
        return op4;
    }
    public  String estadoOperario1() {
        return op5;
    }
    public String estadoOperario2() {
        return op6;
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

    public List<String> setLista() {
        
        return listaOperarios;
    }

}
