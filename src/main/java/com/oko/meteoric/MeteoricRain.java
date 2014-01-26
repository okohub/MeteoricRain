package com.oko.meteoric;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;
import java.io.InputStream;


import java.util.Arrays;
import java.util.TimerTask;
import java.util.Timer;
import java.util.prefs.Preferences;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 * @author oko
 */

public class MeteoricRain {

    Preferences userPref;

    private static MeteoricRain instance = null;

    int playerLevel;

    JFrame frame = new JFrame();
    ImagePanel imagePanel;
    JLabel timeLabel = new JLabel("");
    JButton[] buttons;
    JButton[] CorrectArray;

    Ear Listener1 = new Ear();

    int CorrectIntArray[];

    Point xy = new Point();

    int num;
    int score = 0;
    int remainingTime;

    MusicPlayer mp;

    public static MeteoricRain getInstance() {
        if (instance == null) {
            instance = new MeteoricRain();
        }
        return instance;
    }

    public MeteoricRain() { // constructor
        userPref = Preferences.userNodeForPackage(this.getClass());
        controlInput();
        createPanel();
        createButtonsAndTime();
        setFrameProperties();
        mp = new MusicPlayer();
        mp.start();
        Timer timer = new Timer("CountDown");
        CountDownTask cd = new CountDownTask();
        remainingTime = 61;
        timer.schedule(cd, 0, 1000);
    }

    public void controlInput() {
        String[] selectionValues = {
                "1 - Easy",
                "2 - Medium",
                "3 - Hard"
        };
        String initialSelection = "2 - Medium";
        Object selection = JOptionPane.showInputDialog(null, "Select your game level",
                "Difficulty", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        if (selection == "1 - Easy") {
            playerLevel = 3;
        } else if (selection == "2 - Medium") {
            playerLevel = 4;
        } else if (selection == "3 - Hard") {
            playerLevel = 5;
        } else {
            playerLevel = 3;
        }
        CorrectIntArray = new int[playerLevel];
        buttons = new JButton[playerLevel];
        CorrectArray = new JButton[playerLevel];
    }

    public void createPanel() {
        try {
            imagePanel = new ImagePanel(this.getClass().getResource("/" + "GameBackground.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagePanel.setOpaque(true);
        frame.setContentPane(imagePanel);
    }

    public void createButtonsAndTime() {

        timeLabel.setBounds(imagePanel.getWidth() - 25, imagePanel.getHeight() - 20, 25, 20);
        timeLabel.setText("" + remainingTime);
        timeLabel.setFont(new java.awt.Font("Arial Bold", 3, 15));
        timeLabel.setForeground(new java.awt.Color(255, 0, 0));
        timeLabel.setVisible(true);
        imagePanel.add(timeLabel);
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/" + "MeteorIcon.jpg"));
        for (int i = 0; i < playerLevel; i++) {
            buttons[i] = new JButton();
            buttons[i].setIcon(icon);
            buttons[i].setBackground(java.awt.Color.CYAN);
            buttons[i].setText("" + (int) +(-100 + Math.random() * 200));
            CorrectIntArray[i] = Integer.parseInt(buttons[i].getText());
            buttons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
            buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
            buttons[i].addActionListener(Listener1);
            randomCoordinates();
            buttons[i].setBounds((int) this.xy.getX(), (int) this.xy.getY(), 64, 80);
            buttons[i].setOpaque(true);
            if (i >= 1) {
                calculatePosition(i);
            }
        }
        Arrays.sort(CorrectIntArray);
        for (int z = 0; z < playerLevel; z++) {
            for (int j = 0; j < CorrectIntArray.length; j++) {
                if (Integer.parseInt(buttons[j].getText()) == CorrectIntArray[z]) {
                    CorrectArray[z] = buttons[j];
                }
            }
        }
        for (int h = 0; h < playerLevel; h++) {
            imagePanel.add(CorrectArray[h]);
            CorrectArray[h].setVisible(true);
        }
        imagePanel.repaint();
    }

    public void calculatePosition(int i) {
        boolean overlap = true;
        while (overlap) {
            Point compare[] = MeteoricUtils.getButtonPointSpace(buttons[i]); // button which will be put
            overlap = false;
            for (int j = 0; j <= i - 1; j++) {  //for every other button
                Point current[] = MeteoricUtils.getButtonPointSpace(buttons[j]); // take one of them
                for (int y = 0; y < 81; y++) {     // take a x,y point from button which will be put
                    for (int x = 0; x < 65; x++) {
                        int x1 = compare[(y * 65) + x].x;
                        int y1 = compare[(y * 65) + x].y;
                        for (int k = 0; k < 81; k++) {  // look for this point if contains in another button space
                            for (int m = 0; m < 65; m++) {
                                int x2 = current[(k * 65) + m].x;
                                int y2 = current[(k * 65) + m].y;
                                if ((x1 == x2) && (y1 == y2)) { // if contains overlap, get new random coordinate
                                    overlap = true;
                                    randomCoordinates();
                                    buttons[i].setBounds((int) this.xy.getX(), (int) this.xy.getY(), 64, 80);
                                    break;
                                }
                            }
                            if (overlap) {
                                break;
                            }
                        }
                        if (overlap) {
                            break;
                        }                    // break all the loops because we get overlap !
                    }
                    if (overlap) {
                        break;
                    }
                }
                if (overlap) {
                    break;
                }
            }
        }
    }

    public void setFrameProperties() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                int n = JOptionPane.showConfirmDialog(
                        frame,
                        "Do you really want to stop playing ?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        frame.setTitle("Meteoric Rain");
        frame.setLocation(180, 30);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(new Dimension(imagePanel.getWidth(), imagePanel.getHeight()));
        frame.pack();
        frame.pack(); // one more pack for fit quality
        frame.setVisible(true);
    }

    public void clearArrays() {
        for (int i = 0; i < playerLevel; i++) {
            buttons[i].setVisible(false);
            CorrectArray[i].setVisible(false);
            imagePanel.remove(buttons[i]);
            imagePanel.remove(CorrectArray[i]);
        }
        for (int i = 0; i < playerLevel; i++) {
            buttons[i] = null;
            CorrectArray[i] = null;
        }
    }

    public void randomCoordinates() {
        int w = (int) (Math.random() * (imagePanel.getWidth()));
        int h = (int) (Math.random() * (imagePanel.getHeight()));

        if (w > imagePanel.getWidth() - 84)
            w = imagePanel.getWidth() - 84;

        if (h > imagePanel.getHeight() - 100)
            h = imagePanel.getHeight() - 100;
        this.xy.setLocation(w, h);
    }

    public void exit() {
        if (userPref.getInt("highScore", 0) < score) {
            userPref.putInt("highScore", score);
            JOptionPane.showMessageDialog(null, "Congratulations, you have the new high score !");
        }
        try {
            MeteoricRain.getInstance().frame.dispose();
            mp.stopMusic();
            MainScreen.getInstance().frame.setVisible(true);
            MeteoricRain.instance = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class Ear implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == CorrectArray[num]) { // correct button
                score += (3 * playerLevel);
                CorrectArray[num].setVisible(false);
                num++;
                if (num != 0 && (num % CorrectArray.length == 0)) {
                    num = 0;
                    score += (5 * playerLevel);
                    clearArrays();
                    createButtonsAndTime();
                }
            } else {             // wrong attempt
                num = 0;
                score -= (3 * playerLevel);
                clearArrays();
                createButtonsAndTime();
            }
        }
    }

    private class CountDownTask extends TimerTask {

        public void run() {

            if (remainingTime >= 1) {
                remainingTime--;
                timeLabel.setText("" + remainingTime);
            }
            if (remainingTime == 0) {
                this.cancel();
                JOptionPane.showMessageDialog(null, "There is no time GAME OVER\n " +
                        "Your score is : " + score);
                exit();
            }
        }
    }

    private class MusicPlayer extends Thread {

        Clip clip;
        public void run() {
            try {
                InputStream is = this.getClass().getResourceAsStream("/" + "gamemusic.wav");
                AudioInputStream audio = AudioSystem.getAudioInputStream(is);
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void stopMusic() {
            clip.stop();
        }

    }
}
