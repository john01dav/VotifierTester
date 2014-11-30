package src.john01dav.votifiertester.gui;

import src.john01dav.votifiertester.customswing.JPlaceholderTextArea;
import src.john01dav.votifiertester.customswing.JPlaceholderTextField;

import javax.swing.*;

public class VotifierTester extends JFrame {
    private JButton newButton, saveButton, openButton, sendVote;
    private JPlaceholderTextField serviceName, serverIP, username, ipAddress;
    private JScrollPane publicKeyScrollPane;
    private JPlaceholderTextArea publicKey;

    public static void main(String[] args){

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can't load L&F, falling back to default.");
        }

        new VotifierTester().start();
    }

    private void start(){
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        newButton = new JButton("New");
        add(newButton);

        saveButton = new JButton("Save");
        add(saveButton);

        openButton = new JButton("Open");
        add(openButton);

        sendVote = new JButton("Send Vote!");
        add(sendVote);

        serviceName = new JPlaceholderTextField();
        serviceName.setPlaceholder("Service Name");
        add(serviceName);

        serverIP = new JPlaceholderTextField();
        serverIP.setPlaceholder("Server IP:PORT");
        add(serverIP);

        username = new JPlaceholderTextField();
        username.setPlaceholder("Minecraft Username");
        add(username);

        ipAddress = new JPlaceholderTextField();
        ipAddress.setPlaceholder("Voter IP Address");
        add(ipAddress);

        publicKey = new JPlaceholderTextArea();
        publicKeyScrollPane = new JScrollPane(publicKey);
        publicKey.setPlaceholder("Public Key");
        publicKey.setLineWrap(true);
        add(publicKeyScrollPane);

        //newButton
        springLayout.putConstraint(SpringLayout.NORTH, newButton, 10, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, newButton, 10, SpringLayout.WEST, getContentPane());

        //saveButton
        springLayout.putConstraint(SpringLayout.NORTH, saveButton, 10, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, saveButton, -5, SpringLayout.WEST, openButton);
        springLayout.putConstraint(SpringLayout.WEST, saveButton, 5, SpringLayout.EAST, newButton);

        //openButton
        springLayout.putConstraint(SpringLayout.NORTH, openButton, 10, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, openButton, -10, SpringLayout.EAST, getContentPane());

        //sendVote
        springLayout.putConstraint(SpringLayout.EAST, sendVote, -10, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, sendVote, -10, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, sendVote, 10, SpringLayout.WEST, getContentPane());

        //serviceName
        springLayout.putConstraint(SpringLayout.NORTH, serviceName, 5, SpringLayout.SOUTH, openButton);
        springLayout.putConstraint(SpringLayout.EAST, serviceName, -9, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, serviceName, 10, SpringLayout.WEST, getContentPane());

        //serverIP
        springLayout.putConstraint(SpringLayout.NORTH, serverIP, 5, SpringLayout.SOUTH, serviceName);
        springLayout.putConstraint(SpringLayout.EAST, serverIP, -9, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, serverIP, 10, SpringLayout.WEST, getContentPane());

        //username
        springLayout.putConstraint(SpringLayout.NORTH, username, 5, SpringLayout.SOUTH, serverIP);
        springLayout.putConstraint(SpringLayout.EAST, username, -9, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, username, 10, SpringLayout.WEST, getContentPane());

        //ipAddress
        springLayout.putConstraint(SpringLayout.NORTH, ipAddress, 5, SpringLayout.SOUTH, username);
        springLayout.putConstraint(SpringLayout.EAST, ipAddress, -9, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, ipAddress, 10, SpringLayout.WEST, getContentPane());

        //publickey
        springLayout.putConstraint(SpringLayout.NORTH, publicKeyScrollPane, 5, SpringLayout.SOUTH, ipAddress);
        springLayout.putConstraint(SpringLayout.EAST, publicKeyScrollPane, -9, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, publicKeyScrollPane, 10, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, publicKeyScrollPane, -5, SpringLayout.NORTH, sendVote);

        sendVote.addActionListener(new VoteListener(this));

        newButton.setActionCommand("new");
        saveButton.setActionCommand("save");
        openButton.setActionCommand("open");

        VoteIOListener voteIOListener = new VoteIOListener(this);
        newButton.addActionListener(voteIOListener);
        saveButton.addActionListener(voteIOListener);
        openButton.addActionListener(voteIOListener);

        setTitle("VotifierTester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize((newButton.getPreferredSize().width + saveButton.getPreferredSize().width + openButton.getPreferredSize().width + 30) * 2, 512);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public String getServiceName(){
        return serviceName.getText();
    }

    public String getServerIP(){
        return serverIP.getText();
    }

    public String getUsername(){
        return username.getText();
    }

    public String getVoterIP(){
        return ipAddress.getText();
    }

    public String getPublicKey(){
        return publicKey.getText();
    }

    public void setServiceName(String value){
        serviceName.setText(value);
    }

    public void setServerIP(String value){
        serverIP.setText(value);
    }

    public void setUsername(String value){
        username.setText(value);
    }

    public void setVoterIP(String value){
        ipAddress.setText(value);
    }

    public void setPublicKey(String value){
        publicKey.setText(value);
    }

}
