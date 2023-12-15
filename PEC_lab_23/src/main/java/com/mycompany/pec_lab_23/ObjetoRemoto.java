/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pec_lab_23;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author gonzalo
 */
public class ObjetoRemoto extends UnicastRemoteObject implements Interfaz_servidor {
    private Banco banco;

    public ObjetoRemoto(Banco banco) throws RemoteException {
        this.banco = banco;
    }

    public String cajero1() throws RemoteException {
        return banco.estadoCajero1();
    }

    public String cajero2() throws RemoteException {
        return banco.estadoCajero2();
    }

    public String cajero3() throws RemoteException {
        return banco.estadoCajero3();
    }

    public String cajero4() throws RemoteException {
        return banco.estadoCajero4();
    }

    public String SaldoCajero1() throws RemoteException {
        return banco.setSaldoCajero1();
    }

    public String SaldoCajero2() throws RemoteException {
        return banco.setSaldoCajero2();
    }

    public String SaldoCajero3() throws RemoteException {
        return banco.setSaldoCajero3();
    }

    public String SaldoCajero4() throws RemoteException {
        return banco.setSaldoCajero4();
    }

    public String SaldoBanco() throws RemoteException {
        return banco.setSaldo();
    }

    public String operario1() throws RemoteException {
        return banco.estadoOperario1();
    }

    public String operario2() throws RemoteException {
        return banco.estadoOperario2();
    }

}
