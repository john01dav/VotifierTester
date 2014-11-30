package src.john01dav.votifiertester.gui;

import src.john01dav.votifiertester.io.Vote;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VoteListener implements ActionListener {
    private VotifierTester votifierTester;

    public VoteListener(VotifierTester votifierTester){
        this.votifierTester = votifierTester;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Vote vote = Vote.fromVotifierTester(votifierTester);
            vote.vote();
        }catch(IOException e2){
            JOptionPane.showMessageDialog(votifierTester, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
