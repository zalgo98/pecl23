/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pec_lab_23;

/**
 *
 * @author gonzalo
 */
public class Cajero {
    private Banco banco;
    private int saldo,max;
    private Operario operario;
    private int id;
    private boolean lleno= false;
    private boolean vacio= false;
    boolean ocupado=false;
    
    public Cajero(int id, Banco banco, int saldo, int max, Operario operario) {
        this.banco=banco;
        this.saldo=saldo;
        this.max=max;
        this.operario=operario;
        this.id=id;
    }
    public void ingresarDinero(Persona idpersona, int dinero){
        ocupado=true;
        saldo +=dinero;
    }
    public void extraerDinero(Persona idpersona, int dinero){
        ocupado=true;
        saldo -=dinero;
        
    }
    
    public boolean estaVacio(){//comprueba si esta vacio
        return vacio;
        }
    public boolean estaLleno(){//comprueba si esta lleno 
        return lleno;
        }
    public void setVacio(boolean vacio){
        this.vacio=vacio;
    }
    public void setLleno(boolean lleno){
        this.lleno=lleno;
    }
    
    public int idCajero(){//Obtenemos el id del cajero
        return id;
    }
    public int saldoCajero(){//Obtenemos saldo del cajero
        return saldo;
    }
    public void setSaldo(int nSaldo){
        saldo=nSaldo;
    }
    public Operario setOperario(){
        return operario;
    }
}

