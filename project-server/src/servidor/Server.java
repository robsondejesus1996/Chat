/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import controle.ControleAcessos;
import controle.ControleMensagens;
import java.io.IOException;
import java.net.ServerSocket;
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
public class Server {

    private ServerSocket serverSocket;
    private static JSONArray usuarios;
    public static final int PORTASERVIDOR = 5555;

    public static void main(String[] args) {
        try {
            Server servidor = new Server();
            servidor.run();

            while (true) {

            }
        } catch (Exception e) {
            System.out.println("OCORREU UM ERRO NA INICIALIZAÇÃO DO SERVIDOR" + e.getMessage());
        }
    }

    public void run() {
        usuarios = new JSONArray();
        String dados;
        try {
            serverSocket = new ServerSocket(PORTASERVIDOR);
            System.out.println("INICIALIZAÇÃO DO SERVIDOR BEM SUCEDIDA");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                dados = ControleMensagens.dadosRecebidos(socket);
                System.out.println(dados + "dados");
                JSONObject jobj = ControleMensagens.dadosJson(dados);
                if (jobj.get("instrucao") == null) {
                    adicionarUsuario(dados);
                    System.out.println("USUARIO CADASTRADO COM SUCESSO...");
                    System.out.println(usuarios.toJSONString());
                } else {
                    if (jobj.get("instrucao").equals("listar")) {
                        String retorno = obterUsuarios();
                        System.out.println("Usuários conectados:  " + retorno);
                        ControleMensagens.dadosEnviados(retorno, socket);
                    }
                }
                ControleAcessos gcon = new ControleAcessos(socket, dados, this);
                new Thread(gcon).start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

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

    public JSONArray deletarUsuario(String dados) {
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

    public static void definirUsuarios(JSONArray usuarios) {
        Server.usuarios = usuarios;
    }

    public static JSONArray getUsuarios() {
        return usuarios;

    }

}
