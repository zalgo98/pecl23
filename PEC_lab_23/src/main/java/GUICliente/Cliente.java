/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUICliente;

import com.mycompany.pec_lab_23.Interfaz_servidor;
import java.rmi.Naming;

/**
 *
 * @author gonzalo
 */
public class Cliente {

    public static void main(String[] args) {
        InterfazCliente intercliente = new InterfazCliente();
        intercliente.setVisible(true);
        try {
            Interfaz_servidor server = (Interfaz_servidor) Naming.lookup("//localhost/Remoto");
            while (true) {
                String cajero1 = server.cajero1();
                String cajero2 = server.cajero2();
                String cajero3 = server.cajero3();
                String cajero4 = server.cajero4();
                String SaldoCajero1 = server.SaldoCajero1();
                String SaldoCajero2 = server.SaldoCajero2();;
                String SaldoCajero3 = server.SaldoCajero3();;
                String SaldoCajero4 = server.SaldoCajero4();;
                String SaldoBanco = server.SaldoBanco();
                String operario1 = server.operario1();
                String operario2 = server.operario2();
                intercliente.rem_Cliente(cajero1, cajero2, cajero3, cajero4, SaldoCajero1,SaldoCajero2, SaldoCajero3, SaldoCajero4, SaldoBanco, operario1, operario2);
            }
        } catch (Exception e) {

        }
    }
}
