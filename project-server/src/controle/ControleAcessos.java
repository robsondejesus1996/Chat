/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import servidor.Server;

/**
 *
 * @author Robson de Jesus
 */
public class ControleAcessos implements Runnable{
    
    private Socket sock;
    private String infoDados;
    private Server server;
    private boolean executando;

    @Override
    public void run() {
        String dados;
        executando = true;
        while (executando) {
            dados = ControleMensagens.dadosRecebidos(sock);
            JSONObject jobj = ControleMensagens.dadosJson(dados);
            if (jobj.get("instrucao") == null) {
                server.adicionarUsuario(dados);
                System.out.println("Novo usuário cadastrado!");
            } else {
                if (jobj.get("instrucao").equals("listar")) {
                    String retorno = server.obterUsuarios();
                    ControleMensagens.dadosEnviados(retorno, sock);
                } else {
                    if (jobj.get("instrucao").equals("off")) {
                        server.definirUsuarios(server.deletarUsuario(dados));
                        executando = false;
                        try {
                            sock.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ControleAcessos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        if (jobj.get("instrucao").equals("excluir")) {
                            server.definirUsuarios(server.deletarUsuario(dados));
                        }
                    }
                }
            }
        }
    }
    
    
    public ControleAcessos(Socket socket, String dados, Server server){
        this.sock = socket;
        this.infoDados = dados; 
        this.server = server;
        this.executando = false;
    }
    
    public boolean rodando(){
        return executando;
    }
    
    public void definirExecucao(){
        this.executando = executando; 
    }
    
}
