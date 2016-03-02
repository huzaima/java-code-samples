/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Huzaima
 */
public class ChatClient extends JFrame {

    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private static JTextArea chatLog;
    private static JTextArea message;
    private static JButton jButton1;
    private static boolean exit;

    ChatClient() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatLog = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatLog.setColumns(20);
        chatLog.setRows(5);
        jScrollPane1.setViewportView(chatLog);

        message.setColumns(20);
        message.setRows(5);
        jScrollPane2.setViewportView(message);

        jButton1.setText("Send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addContainerGap())
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jButton1)
                                        .addContainerGap(32, Short.MAX_VALUE))))
        );

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, InterruptedException {

        ChatClient clientGUI = new ChatClient();
        clientGUI.setResizable(false);
        clientGUI.setTitle("Chat Client");
        clientGUI.setVisible(true);
        chatLog.getCaret().setVisible(false);
        chatLog.setEditable(false);

        try {
            final Socket connection = new Socket(InetAddress.getLocalHost(), 5000);

            chatLog.append("Setting up chat on your computer " + InetAddress.getLocalHost().getHostName() + " on "
                    + InetAddress.getLocalHost().getHostAddress());

            jButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //Execute when button is pressed
                        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                        out.writeUTF(message.getText());
                        out.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    chatLog.append(Calendar.getInstance().getTime().toString() + " Server: " + message.getText());
                    chatLog.append("\n");
                    chatLog.setCaretPosition(chatLog.getDocument().getLength());
                    message.setText("");
                }
            });

            AWTEventMonitor.addWindowListener(new WindowListener() {

                @Override
                public void windowClosing(WindowEvent e) {
                    exit = true;
                    try {
                        connection.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void windowOpened(WindowEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowIconified(WindowEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowActivated(WindowEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

            ClientInputThread thread = new ClientInputThread(connection.getInputStream());
            thread.start();

        } catch (ConnectException ce) {
            chatLog.append("Either IP is wrong or there is no server listening on given port" + "\n");
            chatLog.setCaretPosition(chatLog.getDocument().getLength());
            chatLog.append("Exiting in 5 seconds");
            chatLog.setCaretPosition(chatLog.getDocument().getLength());
            Thread.sleep(5000);
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class ClientInputThread extends Thread {

        DataInputStream in;

        ClientInputThread(InputStream i) {
            in = new DataInputStream(i);
        }

        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            while (!exit) {
                try {
                    String message = in.readUTF();
                    chatLog.append("\n");
                    chatLog.append(calendar.getTime().toString() + " Client: " + message);
                    chatLog.append("\n");
                    chatLog.setCaretPosition(chatLog.getDocument().getLength());
                } catch (IOException ex) {
                }
            }
        }
    }
}
