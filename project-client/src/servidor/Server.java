/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import controle.ControleMensagens;
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
public class Server {

    private static JSONArray usuarios;

    public void adicionarUsuario(String dados) {
        try {
            JSONParser jParser = new JSONParser();

            Object obj = jParser.parse(dados);

            JSONArray listUsers = (JSONArray) obj;
            JSONObject j = new JSONObject();
            j = (JSONObject) listUsers.get(0);
            usuarios.add(j);
        } catch (ParseException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public JSONArray deletarUsuario(String dados){
        JSONObject usuario = ControleMensagens.dadosJson(dados);
        System.out.println("Usuário excluido" + usuario.toJSONString());
        JSONArray newLista = new JSONArray();
        JSONObject jobj = new JSONObject();
        for (Object user : usuarios) {
            jobj = (JSONObject) user;
            if (jobj.get("porta").equals(usuario.get("porta"))) {
            } else {
                newLista.add(user);
            }
        }
        return newLista;
    }

    public String obterUsuarios() {
        String us = JSONArray.toJSONString(usuarios);
        return us;
    }
    
    public static void definirUsuarios(JSONArray usuarios){
        Server.usuarios = usuarios;
    }

}
