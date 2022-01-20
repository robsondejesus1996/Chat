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
            dados = ControleMensagens.dadosRecebidos(sock);
            JSONObject jobj = ControleMensagens.dadosJson(dados);
            String clientInfos = jobj.get("nome") + ";" + jobj.get("enderecoip") + ";" + jobj.get("porta");
            if (jobj.get("instrucao") == null) {
                if (chatRun == false) {
                    chatRun = true;
                    conversa = new Conversa(opcoes, sock, clientInfos);
                }else{
                    conversa.mensagemDados(dados);
                }
            } else {
                if (jobj.get("instrucao").equals("off")) {
                    if (chatRun) {
                        chatRun = false;
                        try {
                            sock.close();
                        } catch (IOException ex) {
                            System.err.println("Erro ao encerrar chat");
                        }
                        conversa.dispose();
                    }
                    executando = false;
                }
            }
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
