/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import controle.ControleDadosChat;
import controle.ControleMensagens;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import modelo.Cliente;
import org.json.simple.JSONObject;

/**
 *
 * @author Robson de Jesus
 */
public class OpcoesUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form OpcoesUsuarios
     */
    
    
    private Cliente c;
    private ArrayList<String> usuarios;
    private ServerSocket serverSocketCliente;
    private boolean executando;

    
    
    
    public OpcoesUsuarios(Cliente c) {
        executando = false;
        serverSocketCliente = null;
        this.jList = new JList<>();
        this.usuarios = new ArrayList<String>();
        this.c = c;
        initComponents();
        clientStartSrv(this, Integer.parseInt(c.getPorta()));
    }
    
    
    
    private void iniciarChat() {
        int index = jList.getSelectedIndex();
        if (index != -1) {
            String dados = jList.getSelectedValue().toString();
            String[] inf = dados.split(";");
            System.out.println(inf[0]);
            try {
                Socket socket = new Socket(inf[1], Integer.parseInt(inf[2]));
                ControleMensagens.dadosEnviados(ControleMensagens.informacoesUsuario(c), socket);
                ControleDadosChat gc = new ControleDadosChat(socket, this);
                gc.definirConversa(new Conversa(this, socket, dados));
                gc.definirConversaRodando(true);
                new Thread(gc).start();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "USUÁRIO ESTÁ INDISPONÍVEL NO MOMENTO! ATUALIZE A LISTA");
                ControleMensagens.dadosEnviados(ControleMensagens.deletarMensagem(inf[2]), c.obterUsuarioSocket());
                System.err.println("Usuário indisponível: " + e);

            }
        }
    }
    
    private void clientStartSrv(OpcoesUsuarios menu, int porta){
        new Thread() {
            @Override
            public void run() {
                executando = true;
                try {
                    serverSocketCliente = new ServerSocket(porta);
                    System.out.println("Server cliente iniciado");
                    while (executando) {
                        Socket socket = serverSocketCliente.accept();
                        ControleDadosChat gc = new ControleDadosChat(socket, menu);
                        new Thread(gc).start();
                    }
                } catch (Exception e) {
                    System.err.println("Start server client" + e);
                }
            }
        }.start();
    }
    
    
    private void listaAtualizacao() {
        ControleMensagens.dadosEnviados(ControleMensagens.listarMensagem(), c.obterUsuarioSocket());
        String retorno = ControleMensagens.dadosRecebidos(c.obterUsuarioSocket());
        System.out.println(retorno + "  retorno de usuários!!");
        c.listagemUsuarios(retorno);
        usuarios.clear();
        jList.removeAll();

        DefaultListModel model = new DefaultListModel();
        JSONObject jobj = new JSONObject();
        for (Object user : c.getQtdUsuariosOnlines()) {
            jobj = (JSONObject) user;
            if (jobj.get("porta").equals(c.getPorta())) {
            } else {
                String c = jobj.get("nome") + ";" + jobj.get("enderecoip") + ";" + jobj.get("porta");
                model.addElement(c);
            }
        }
        jList.setModel(model);

    }

    
        
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btiniciarChat = new javax.swing.JButton();
        jlNome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        btiniciarChat1 = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jlNome1 = new javax.swing.JLabel();

        btiniciarChat.setText("Iniciar chat");
        btiniciarChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btiniciarChatActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuários on-line"));
        jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList.setName(""); // NOI18N
        jList.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(jList);

        btiniciarChat1.setText("Iniciar chat");
        btiniciarChat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btiniciarChat1ActionPerformed(evt);
            }
        });

        btAtualizar.setText("atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuário:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btiniciarChat1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlNome, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btiniciarChat1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Cliente getC() {
        return c;
    }

    public JLabel getJlNome() {
        return jlNome;
    }

    public void setJlNome(JLabel jlNome) {
        this.jlNome = jlNome;
    }
    
    private void btiniciarChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btiniciarChatActionPerformed
        iniciarChat();
    }//GEN-LAST:event_btiniciarChatActionPerformed

    private void btiniciarChat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btiniciarChat1ActionPerformed
        iniciarChat();
    }//GEN-LAST:event_btiniciarChat1ActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        listaAtualizacao();
    }//GEN-LAST:event_btAtualizarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btiniciarChat;
    private javax.swing.JButton btiniciarChat1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlNome1;
    // End of variables declaration//GEN-END:variables
}
