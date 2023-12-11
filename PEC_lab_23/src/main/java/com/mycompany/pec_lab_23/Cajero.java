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
    private boolean lleno= false;
    private boolean vacio= false;
    
    public Cajero(int id, Banco banco, int inicial, int max) {
        this.banco=banco;
        this.inicial=inicial;
        this.max=max;
        this.operario=operario;
        this.id=id;
    }
    public void ingresarDinero(Persona idpersona, int dinero){
        inicial +=dinero;
        if (max<=inicial){
        lleno= true;
        inicial-=dinero;
        }
    }
    public void extraerDinero(Persona idpersona, int dinero){
        inicial -=dinero;
        if (0>=inicial){
        vacio= true;
        inicial +=dinero;
        }
    }
    
    public boolean estaVacio(){
        return vacio;
        }
    public boolean estaLleno(){ 
        return lleno;
        }
    public int vaciarCajero(){//Faltan los operarios
        int retirada=inicial - 50000;
        return retirada;
    }
    public void rellenaCajero(int dinero){//faltan los operarios
        inicial+= dinero;
    }
    public int idCajero(){//Obtenemos el id del cajero
        return id;
    }
}

