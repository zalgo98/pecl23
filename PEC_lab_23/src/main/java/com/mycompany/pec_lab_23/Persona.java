/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pec_lab_23;

import java.util.Random;
/**
 *
 * @author gonzalo
 */
public class Persona extends Thread{
    
    private static final Random random = new Random();
    private final Cajero cajero;
    private final String idPersona;
    private boolean operacionCompletada; // flag

    public Persona(String idPersona, Cajero cajero) {
        this.cajero = cajero;
        this.idPersona = idPersona;
        this.operacionCompletada = false;
    }
    
    public void run() {
        while (!operacionCompletada) {
            try {
                Thread.sleep(random.nextInt(3000) + 2000); // Tiempo de espera aleatorio entre 2 y 4 segundos
                int operacion = random.nextInt(2); // 0 para ingresar, 1 para extraer
                int cantidad = (random.nextInt(6) + 5) * 1000; // Importe aleatorio entre 5000 y 10000
                
                if (operacion == 0) {
                    cajero.ingresarDinero(idPersona,cantidad); // cajero. metti anche id persona 
                } else {
                    cajero.extraerDinero(idPersona,cantidad); // cajero. metti anche id persona 
                }
                
                operacionCompletada = true;
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
