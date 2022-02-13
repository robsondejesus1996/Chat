/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import controle.ControleMensagens;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;

/**
 *
 * @author Robson de Jesus
 */
public class Conversa extends javax.swing.JFrame {

    /**
     * Creates new form Conversa
     */
    
    private OpcoesUsuarios menu;
    private Socket socket;
    private String mensagens;
    
    public Conversa() {
        initComponents();
    }

    public Conversa(OpcoesUsuarios menu, Socket socket, String dados) {
        this.mensagens = "";
        this.socket = socket;
        this.menu = menu;
        initComponents();
        jLNome.setText(dados.split(";")[0]);
        edpChat.setEditable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    
    
    public void mensagemDados(){
        if (tfTexto.getText().length() > 0) {
            String msg = ControleMensagens.msgChat(menu.getC(), tfTexto.getText());
            DateFormat df = new SimpleDateFormat("hh:mm");
            String hora = df.format(new Date());
            edpChat.setText(mensagens += hora + " " + "Você: " + tfTexto.getText() + "\n");
            tfTexto.setText("");
            try {
                ControleMensagens.dadosEnviados(msg, socket);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "OCORREU UM ERRO DE CONEXAO, PROVAVELMENTE O USUÁRIO SAIU DA APLICAÇÃO");
                this.dispose();
            }
        }
    }
    
    
    public void recebeMensagem(String dados) {
        JSONObject jobj = ControleMensagens.dadosJson(dados);
        String nome = (String) jobj.get("nome");
        String hora = (String) jobj.get("hora");
        String msg = (String) jobj.get("mensagem");
        edpChat.setText(mensagens += hora + " " + nome + ": " + msg + "\n");
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btEnviar = new javax.swing.JButton();
        tfTexto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        edpChat = new javax.swing.JEditorPane();
        jLNome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btEnviar.setText("Enviar");
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });
        btEnviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btEnviarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btEnviarKeyReleased(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setViewportView(edpChat);

        jLabel1.setText("Falando com: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(tfTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLNome, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(17, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 304, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(61, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed
        mensagemDados();

    }//GEN-LAST:event_btEnviarActionPerformed

    
    
    private void btEnviarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btEnviarKeyPressed
            
        
        if(evt.getKeyChar() == KeyEvent.VK_ENTER){
            mensagemDados();
        }
    }//GEN-LAST:event_btEnviarKeyPressed

    private void btEnviarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btEnviarKeyReleased

        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
        }
    }//GEN-LAST:event_btEnviarKeyReleased

    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {

    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        ControleMensagens.dadosEnviados(ControleMensagens.OfflineMensagem(""), socket);
        this.dispose();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Conversa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Conversa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Conversa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Conversa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Conversa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEnviar;
    private javax.swing.JEditorPane edpChat;
    private javax.swing.JLabel jLNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfTexto;
    // End of variables declaration//GEN-END:variables
}
