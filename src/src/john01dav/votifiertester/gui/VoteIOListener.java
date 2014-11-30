package src.john01dav.votifiertester.gui;

import src.john01dav.votifiertester.io.Vote;
import src.john01dav.votifiertester.io.VoteIO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VoteIOListener implements ActionListener {
    private VotifierTester votifierTester;
    private JFileChooser jFileChooser;

    public VoteIOListener(VotifierTester votifierTester){
        this.votifierTester = votifierTester;

        jFileChooser = new JFileChooser();
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setFileFilter(new VoteFileFilter());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal;

        switch(e.getActionCommand()){
            case "new":
                votifierTester.setServiceName("");
                votifierTester.setServerIP("");
                votifierTester.setUsername("");
                votifierTester.setVoterIP("");
                votifierTester.setPublicKey("");
            break;
            case "save":
                returnVal = jFileChooser.showSaveDialog(votifierTester);

                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File file = jFileChooser.getSelectedFile();
                    Vote vote = Vote.fromVotifierTester(votifierTester);

                    if(!file.getName().endsWith(".vtc")){
                        file = new File(file.getParent(), file.getName() + ".vtc");
                    }

                    try{
                        VoteIO.save(file, vote);
                        JOptionPane.showMessageDialog(votifierTester, "Vote Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }catch(IOException e2){
                        JOptionPane.showMessageDialog(votifierTester, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            break;
            case "open":
                returnVal = jFileChooser.showOpenDialog(votifierTester);

                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File file = jFileChooser.getSelectedFile();

                    try{
                        Vote vote = VoteIO.load(file);

                        votifierTester.setServiceName(vote.getServiceName());
                        votifierTester.setServerIP(vote.getServerIP());
                        votifierTester.setUsername(vote.getUsername());
                        votifierTester.setVoterIP(vote.getVoterIP());
                        votifierTester.setPublicKey(vote.getPublicKey());

                    }catch(Exception e2){
                        JOptionPane.showMessageDialog(votifierTester, e2.getMessage(), e2.getClass().getCanonicalName(), JOptionPane.ERROR_MESSAGE);
                    }

                }
            break;
        }
    }

}
