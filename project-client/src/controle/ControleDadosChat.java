/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import telas.Conversa;
import telas.Entrar;
import telas.OpcoesUsuarios;

/**
 *
 * @author Robson de Jesus
 */
public class ControleDadosChat implements Runnable {

    private String infoUser;
    private Socket sock;
    private boolean chatRun;
    private boolean executando;
    private Conversa conversa;
    private OpcoesUsuarios opcoes;
    

    @Override
    public void run() {
        String dados;
        executando = true;
        while (executando) {
            
        }
    }

    public ControleDadosChat(Socket socket, OpcoesUsuarios opcoes) {
        this.infoUser = null;
        this.sock = socket;
        this.chatRun = false;
        this.executando = false;
        this.conversa = null;
        this.opcoes = opcoes;
    }

    public Conversa obterConversa() {
        return conversa;
    }

    public void definirConversa(Conversa conver) {
        this.conversa = conver;
    }

    public void definirConversaRodando(boolean chatRun) {
        this.chatRun = chatRun;
    }

    public boolean eConversaRodando() {
        return chatRun;
    }
}
