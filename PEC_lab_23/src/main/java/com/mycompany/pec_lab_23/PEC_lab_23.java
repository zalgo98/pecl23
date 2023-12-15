/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.pec_lab_23;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gonzalo
 */
public class PEC_lab_23 {

    public static void main(String[] args) {
        try {
            Banco banco = new Banco(250000);
            Operario op1 = new Operario(banco, "operario 1");//hilo de operario
            Operario op2 = new Operario(banco, "operario 2");
            Cajero cajero1 = new Cajero(1, banco, 50000, 100000, op1);//hilo cajero
            Cajero cajero2 = new Cajero(2, banco, 50000, 100000, op1);
            Cajero cajero3 = new Cajero(3, banco, 50000, 100000, op2);
            Cajero cajero4 = new Cajero(4, banco, 50000, 100000, op2);
            op1.start();
            op2.start();

            Cajero[] cajeros = {cajero1, cajero2, cajero3, cajero4}; // Array de cajeros
            //Servidor rmi
            Random rand = new Random();
            ObjetoRemoto server = new ObjetoRemoto(banco);
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("Localhost/ObjetRMI", server);

            GUI interfaz = new GUI(banco);
            interfaz.setVisible(true);
            for (int i = 1; i < 201; i++) {
                int tiempoLlegada = rand.nextInt(1000);
                int cajeroId = rand.nextInt(4); // Generar índice aleatorio

                interfaz.mostrar();
                Thread.sleep(tiempoLlegada);
                Cajero cajeroSeleccionado = cajeros[cajeroId]; // Obtener cajero según el índice aleatorio
                String nombrePersona = "Persona" + (i + 1);
                Persona persona = new Persona("persona " + i, cajeroSeleccionado, banco);
                persona.start();
            }

            while (true) {
                interfaz.mostrar();
            }
        } catch (Exception e) {

        }
    }
}
