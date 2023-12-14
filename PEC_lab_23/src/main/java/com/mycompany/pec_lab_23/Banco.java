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
    ArrayList<String> listaOperarios;
    
    private boolean pausa = false;
    private boolean pausaOp1 = false;
    private boolean pausaOp2 = false;
    public Banco(int dineroBanco) {

        this.dineroBanco = dineroBanco;
        this.cajero = cajero;
        this.operario = operario;
        this.persona = persona;
        this.listaOperarios = new ArrayList<>();

    }

    public synchronized void ingresarDinero(int cantidad, Operario operario) {
        
        dineroBanco += cantidad;
        
        notify();
    }

    public synchronized void extraerDinero(int cantidad, Operario operario) {
        while (dineroBanco < cantidad) {
            try {

                wait(); // Esperar si no hay suficiente dinero en el banco central
            } catch (InterruptedException e) {
            }
        }
        
        dineroBanco -= cantidad;
        notify();
    }

    public synchronized void ingresarCajero(Persona persona, Cajero cajero, int cantidad) {
        if(pausa==false){
        cantidad += 90000;

        if (100000 < cajero.saldoCajero() + cantidad) {//Comprueba si al ingresar el dinero nos vamos a pasar
            int diferencia = 100000 - cajero.saldoCajero();
            cajero.ingresarDinero(persona, diferencia);//Deja el saldo al maximo
            
            cantidad -= diferencia;
            cajero.setLleno(true);
            operario = cajero.setOperario();
            operario.vaciarCajero(cajero);
            trabajador = operario.setID() + "-C" + cajero.idCajero() + " + " + cantidad;
        
            getOperario(operario);
        } else {
            cajero.ingresarDinero(persona, cantidad);
        }
        operacion = (persona.getIdPersona() + "- I - " + cantidad);

        getSaldo(cajero);
        
        notify();
        }
    }

    public synchronized void extraerCajero(Persona persona, Cajero cajero, int cantidad) {
        if(pausa==false){
        if (0 > cajero.saldoCajero() - cantidad) {//comprueba si al retirar el dinero nos vamos a pasar
            int diferencia = cantidad - cajero.saldoCajero();
            cajero.extraerDinero(persona, diferencia);//Deja el saldo a cero
            cantidad -= diferencia;
            cajero.setVacio(true);
            operario = cajero.setOperario();
            operario.rellenaCajero(cantidad, cajero);
            getOperario(operario);
            trabajador = operario.setID() + "-C" + cajero.idCajero() + " - " + cantidad;
        } else {
            cajero.extraerDinero(persona, cantidad);
        }
        operacion = (persona.getIdPersona() + "- E - " + cantidad);
        getSaldo(cajero);
       
        notify();
        }
    }

    public void getOperario(Operario operario) {
       
        if ("operario 1".equals(operario.setID())&& pausaOp1==false) {
            System.out.println("lo ejecuta 1");
            op5 = trabajador;
            estadoOperario1();
            listaOperarios.add(op5);
        } else if("operario 2".equals(operario.setID())&&pausaOp2==false) {
            System.out.println("lo ejecuta 2");
            op6 = trabajador;
            estadoOperario2();
            listaOperarios.add(op6);
        }
        
    }

    public void getSaldo(Cajero cajero) { //Obtiene los datos de la operacion de la persona

        switch (cajero.idCajero()) {
            case 1 -> {
                saldo1 = cajero.saldoCajero();//Guarda el saldo del cajero
                op1 = operacion;//Guarda la ultima
                setIdCajero1();//imprime el saldo del cajero 
                estadoCajero1();//imprime la ultima operacion realizada
            }
            case 2 -> {
                saldo2 = cajero.saldoCajero();
                op2 = operacion;
                setIdCajero2();
                estadoCajero2();
            }
            case 3 -> {
                saldo3 = cajero.saldoCajero();
                op3 = operacion;
                setIdCajero3();
                estadoCajero3();
            }
            case 4 -> {
                saldo4 = cajero.saldoCajero();
                op4 = operacion;
                setIdCajero4();
                estadoCajero4();
            }
            default -> throw new AssertionError();
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

    public String estadoOperario1() {
        if(pausaOp1==false){
        return op5;
        }
        else{
            return "pausado 1";
        }
    }

    public String estadoOperario2() {
        if(pausaOp2==false){
        return op6;
        }
        else{
            return "pausado 2";
        }
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
    public void pausar(boolean pausar){
        pausa=pausar;
        pausaOp1=pausar;
        pausaOp2=pausar;
    }
    public void pausarOp1(boolean pausar){
        pausaOp1=pausar;
    }
    public void pausarOp2(boolean pausar){
        pausaOp2=pausar;
    }
}
 
