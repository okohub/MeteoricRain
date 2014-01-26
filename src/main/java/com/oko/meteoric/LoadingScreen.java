package com.oko.meteoric;


import javax.swing.JProgressBar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;

/**
 * @author oko
 */

public class LoadingScreen {

    JLabel jLabel;
    JFrame frame;

    public LoadingScreen(String frameString, String labelString) {
        jLabel = new JLabel(labelString);
        frame = new JFrame(frameString);
        final JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        final JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(jLabel, BorderLayout.NORTH);
        contentPane.add(progressBar, BorderLayout.CENTER);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void kill() {
        frame.dispose();
    }
}
