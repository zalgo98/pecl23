/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.pec_lab_23;

import java.util.Random;

/**
 *
 * @author gonzalo
 */
public class PEC_lab_23 {

    public static void main(String[] args) {
        Banco banco= new Banco(250000);
        Operario op1= new Operario(banco, "operario 1");//hilo de operario
        Operario op2= new Operario(banco, "operario 2");
        Cajero cajero1 = new Cajero(1, banco, 50000, 100000);
        Cajero cajero2 = new Cajero(2, banco, 50000, 100000);
        Cajero cajero3 = new Cajero(3, banco, 50000, 100000);
        Cajero cajero4 = new Cajero(4, banco, 50000, 100000);
        op1.start();
        op2.start();
        Random rand= new Random();
         for (int i = 0; i < 200; i++) {
            int tiempoLlegada = rand.nextInt(1000); 
            try {
                Thread.sleep(tiempoLlegada);
            } catch (InterruptedException e) {}
            String nombrePersona = "Persona" + (i + 1);
            Persona persona = new Persona("persona"+ i, cajero1);//Cambiar cajero a numero aleatorio
            persona.start();
        }
    }
}
