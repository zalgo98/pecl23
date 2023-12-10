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
    private int inicial,max;
    private Operario operario;
    private int id;
    
    public Cajero(int id, Banco banco, int inicial, int max) {
        this.banco=banco;
        this.inicial=inicial;
        this.max=max;
        this.operario=operario;
        this.id=id;
    }
    public void ingresarDinero(String idpersona, int dinero){
        inicial +=dinero;
    }
    public void extraerDinero(String idpersona, int dinero){
        inicial -=dinero;
    }
    
    public boolean estaVacio(){
        boolean vacio= true;
        return vacio;
        }
    public boolean estaLleno(){
        boolean lleno= true;
        return lleno;
        }
    public int vaciarCajero(){
        int retirada=inicial - 50000;
        return retirada;
    }
    public void rellenaCajero(int dinero){
        inicial+= dinero;
    }
    public int idCajero(){//Obtenemos el id del cajero
        return id;
    }
}

