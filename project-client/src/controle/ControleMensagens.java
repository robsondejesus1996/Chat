/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Client;
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

    public static void dadosEnviados(String dados, Socket socket) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            out.writeUTF(dados);
            out.flush();
        } catch (Exception e) {
        }
    }

    public static String dadosRecebidos(Socket socket) {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            dados = in.readUTF();
        } catch (Exception e) {
        }
        return dados;
    }

    public static JSONObject dadosJson(String dados) {
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

    public static String msgChat(Client client, String msg) {
        DateFormat formatacao = new SimpleDateFormat("hh:mm");
        String horario = formatacao.format(new Date());

        JSONObject dadosMensagem = new JSONObject();
        dadosMensagem.put("nome", client.getNome());
        dadosMensagem.put("hora", horario);
        dadosMensagem.put("mensagem", msg);

        JSONArray l = new JSONArray();
        l.add(dadosMensagem);
        return l.toJSONString();
    }

    public static String listarMensagem() {
        JSONObject listar = new JSONObject();
        listar.put("instrucao", "listar");
        JSONArray l = new JSONArray();
        l.add(listar);
        return l.toJSONString();
    }

    public static String deletarMensagem(String porta) {
        JSONObject deletar = new JSONObject();
        deletar.put("instrucao", "excluir");
        deletar.put("porta", porta);
        JSONArray l = new JSONArray();
        l.add(deletar);
        return l.toJSONString();
    }

    public static String OfflineMensagem(String porta) {
        JSONObject off = new JSONObject();
        off.put("instrucao", "off");
        off.put("porta", porta);
        JSONArray l = new JSONArray();
        l.add(off);
        return l.toJSONString();
    }

    public static void logar(Socket socket, Client client) {
        dadosEnviados(informacoesUsuario(client), socket);
        System.out.println("USUARIO CADASTRO COM SUCESSO!");
    }

    public static String informacoesUsuario(Client clint) {
        JSONObject dados = new JSONObject();
        dados.put("nome", clint.getNome());
        dados.put("enderecoip", clint.getIpCliente());
        dados.put("porta", clint.getPorta());

        JSONArray l = new JSONArray();
        l.add(dados);

        String mensagem = JSONArray.toJSONString(l);

        return mensagem;
    }
}
