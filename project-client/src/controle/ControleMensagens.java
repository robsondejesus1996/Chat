/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Robson de Jesus
 */
public class ControleMensagens {
    private static String dados = "";
    
    public static String dadosRecebidos(Socket socket){
       try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            dados = in.readUTF();
        } catch (Exception e) {
        }
        return dados;
    }
}
