/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pec_lab_23;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author gonzalo
 */
public interface Interfaz_servidor extends Remote{
    public String cajero1() throws RemoteException;
    public String cajero2() throws RemoteException;
    public String cajero3() throws RemoteException;
    public String cajero4() throws RemoteException;
    public String SaldoCajero1() throws RemoteException;
    public String SaldoCajero2() throws RemoteException;
    public String SaldoCajero3() throws RemoteException;
    public String SaldoCajero4() throws RemoteException;
    public String SaldoBanco() throws RemoteException;
    public String operario1() throws RemoteException;
    public String operario2() throws RemoteException;
    
}
