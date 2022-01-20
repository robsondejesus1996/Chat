/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Robson de Jesus
 */
public class ControleMensagens {
    private static String dados = "";
    
    
    public static void dadosEnviados(String dados, Socket socket){
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            out.writeUTF(dados);
            out.flush();
        } catch (Exception e) {
        }
    }
    
    public static String dadosRecebidos(Socket socket){
       try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            dados = in.readUTF();
        } catch (Exception e) {
        }
        return dados;
    }
    
    
    
    
    public static JSONObject dadosJson(String dados){
        JSONObject jobj = new JSONObject();
        try {
            JSONParser jParser = new JSONParser();

            Object obj = jParser.parse(dados);

            JSONArray listUsers = (JSONArray) obj;
            jobj = (JSONObject) listUsers.get(0);
        } catch (ParseException ex) {
            Logger.getLogger(ControleMensagens.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobj;
    }
}
