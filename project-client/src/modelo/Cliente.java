/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import servidor.Server;
import telas.Entrar;

/**
 *
 * @author Robson de Jesus
 */
public class Cliente {

    private String nomeCliente;
    private String ipCliente;
    private String porta;
    private ServerSocket serverSocketCliente;
    private String stringServerIp;
    private Socket usuarioSocket;
    private JSONArray qtdUsuariosOnlines;
    private boolean execute;

    public Cliente(String nome, String ip, String porta, String serverIp) {
        this.nomeCliente = nome;
        this.ipCliente = ip;
        this.porta = porta;
        this.stringServerIp = serverIp;
    }

    public Cliente() {
    }

    public void conectarServidor(String ip) throws IOException {
        usuarioSocket = new Socket(ip, Server.PORTASERVIDOR);
        System.out.println("CONECTADO AO SERVIDOR");
    }

    public String obterServidorIp() {
        return stringServerIp;
    }

    public void definirServidorIp(String serverIp) {
        this.stringServerIp = serverIp;
    }

    public Socket obterUsuarioSocket() {
        return usuarioSocket;
    }

    public void definirUsuarioSocket(Socket userSocket) {
        this.usuarioSocket = userSocket;
    }

    public String getNome() {
        return nomeCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getIpCliente() {
        return ipCliente;
    }
    

    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public ServerSocket getServerSocketCliente() {
        return serverSocketCliente;
    }

    public void setServerSocketCliente(ServerSocket serverSocketCliente) {
        this.serverSocketCliente = serverSocketCliente;
    }

    public String getStringServerIp() {
        return stringServerIp;
    }

    public void setStringServerIp(String stringServerIp) {
        this.stringServerIp = stringServerIp;
    }

    public Socket getUsuarioSocket() {
        return usuarioSocket;
    }

    public void setUsuarioSocket(Socket usuarioSocket) {
        this.usuarioSocket = usuarioSocket;
    }

    public JSONArray getQtdUsuariosOnlines() {
        return qtdUsuariosOnlines;
    }

    public void setQtdUsuariosOnlines(JSONArray qtdUsuariosOnlines) {
        this.qtdUsuariosOnlines = qtdUsuariosOnlines;
    }

    public boolean isExecute() {
        return execute;
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }

    public void listagemUsuarios(String mensagem) {
        try {
            JSONParser jParser = new JSONParser();

            Object obj = jParser.parse(mensagem);

            this.qtdUsuariosOnlines = (JSONArray) obj;
        } catch (ParseException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) throws IOException {

        Entrar login = new Entrar();
        login.setVisible(true);

    }
}
