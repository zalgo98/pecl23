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
    
    public Banco(int dineroBanco) {
        
        this.dineroBanco = dineroBanco;
        
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
}
