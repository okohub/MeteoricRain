package com.oko.meteoric;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.lang.reflect.Field;

import java.net.URL;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import java.io.IOException;

/**
 * @author oko
 */

public class MainScreen {


    private static MainScreen instance = null;

    //preferences for all GUI values
    Preferences userPref;

    //Font sizes
    int buttonFontSize;
    int headerFontSize;
    int copyrightFontSize;

    //Colors
    Color buttonColor;
    Color headerColor;
    Color copyrightColor;

    //button values
    int buttonWidth;
    int buttonHeight;
    int buttonStartingW;
    int buttonStartingH;

    //header values
    int headerWidth;
    int headerHeight;
    int headerStartingW;
    int headerStartingH;

    //copyright values
    int copyrightWidth;
    int copyrightHeight;
    int copyrightStartingW;
    int copyrightStartingH;

    //GUI Fonts
    String buttonFontName;
    String headerFontName;
    String copyrightFontName;

    //Font styles
    int copyrightFontStyle;
    int headerFontStyle;
    int buttonFontStyle;

    //Fonts
    Font copyrightFont;
    Font headerFont;
    Font buttonFont;


    //Background Image
    URL background;

    //strings for buttons and labels
    String startString = "START A NEW GAME";
    String highScoreString = "HIGH SCORE";
    String optionsString = "OPTIONS";
    String exitString = "EXIT";
    String headerString = "METEORIC RAIN";
    String copyrightString = "ONUR KAGAN OZCAN - 2012";

    //main frame
    JFrame frame = new JFrame("Meteoric Rain");

    ImagePanel imagePanel;

    //buttons
    JButton startButton = new JButton();
    JButton highScore = new JButton();
    JButton optionsButton = new JButton();
    JButton exitButton = new JButton();

    //label
    JLabel headerLabel = new JLabel();
    JLabel copyrightLabel = new JLabel();

    //actionlistener for buttons
    Ear ear = new Ear();

    public MainScreen() throws IOException {
        userPref = Preferences.userNodeForPackage(this.getClass());
        int n = JOptionPane.showConfirmDialog(
                frame,
                "Would you like to set your settings to default ?",
                "Important",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            try {
                int a = userPref.getInt("highScore", 0);
                userPref.clear();
                userPref.putInt("highScore", a);
            } catch (BackingStoreException e) {
                JOptionPane.showMessageDialog(null,
                        "Oops,looks like something went wrong.\n It is better to run again the game",
                        "Error : Preferences are not cleared",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        background = this.getClass().getResource("/" + userPref.get("background", "MainBackground.jpg"));
        imagePanel = new ImagePanel(background);
        frame.setContentPane(imagePanel);
        initializePrefGUIValues();
        initializeButtonProperties();
        initializeLabelProperties();
        initializeImagePanelProperties();
        initializeFrameProperties();
    }

    public static MainScreen getInstance() throws IOException {
        if (instance == null) {
            instance = new MainScreen();
        }
        return instance;
    }

    public void initializePrefGUIValues() {
        copyrightFontSize = userPref.getInt("copyrightFontSize", 10);
        buttonFontSize = userPref.getInt("buttonFontSize", 35);
        headerFontSize = userPref.getInt("headerFontSize", 55);
        //button values
        buttonWidth = buttonFontSize * 20;
        buttonHeight = buttonFontSize + 1;
        buttonStartingW = userPref.getInt("buttonStartingW", 107);
        buttonStartingH = userPref.getInt("buttonStartingH", 350);
        //header values
        headerWidth = headerFontSize * 15;
        headerHeight = headerFontSize + 1;
        headerStartingW = userPref.getInt("headerStartingW", 251);
        headerStartingH = userPref.getInt("headerStartingH", 2);
        //copyright values
        copyrightWidth = copyrightFontSize * 40;
        copyrightHeight = copyrightFontSize + 1;
        copyrightStartingW = userPref.getInt("copyrightStartingW", 755);
        copyrightStartingH = userPref.getInt("copyrightStartingH", 555);
        //fonts
        try {
            //for colors
            Field field = Class.forName("java.awt.Color").getField(userPref.get("copyrightColor", "orange"));
            copyrightColor = (Color) field.get(null);
            field = Class.forName("java.awt.Color").getField(userPref.get("buttonColor", "orange"));
            buttonColor = (Color) field.get(null);
            field = Class.forName("java.awt.Color").getField(userPref.get("headerColor", "orange"));
            headerColor = (Color) field.get(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Looks like something terrible happened :/");
        }
        copyrightFontName = userPref.get("copyrightFontName", "ICBMSS25.ttf");
        buttonFontName = userPref.get("buttonFontName", "VTKSAnimal2.ttf");
        headerFontName = userPref.get("headerFontName", "ICBMSS25.ttf");
        copyrightFontStyle = userPref.getInt("copyrightFontStyle", 1);
        buttonFontStyle = userPref.getInt("buttonFontStyle", 1);
        headerFontStyle = userPref.getInt("headerFontStyle", 1);
        //copyright Font
        Font uniFont = null;
        InputStream is = this.getClass().getResourceAsStream("/" + copyrightFontName);
        try {
            uniFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            uniFont = new Font(MeteoricUtils.cutExtension(copyrightFontName), copyrightFontStyle, copyrightFontSize);
        }
        try {
            copyrightFont = uniFont.deriveFont(copyrightFontStyle, (float) copyrightFontSize);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        //button Font
        is = this.getClass().getResourceAsStream("/" + buttonFontName);
        uniFont = null;
        try {
            uniFont = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            uniFont = new Font(MeteoricUtils.cutExtension(buttonFontName), buttonFontStyle, buttonFontSize);
        }
        try {
            buttonFont = uniFont.deriveFont(buttonFontStyle, (float) buttonFontSize);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        //header Font
        is = this.getClass().getResourceAsStream("/" + headerFontName);
        uniFont = null;
        try {
            uniFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            uniFont = new Font(MeteoricUtils.cutExtension(headerFontName), headerFontStyle, headerFontSize);
        }
        try {
            headerFont = uniFont.deriveFont(headerFontStyle, (float) headerFontSize);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void initializeButtonProperties() {
        //start button properties
        startButton.setText(startString);
        startButton.setFont(buttonFont);
        startButton.setForeground(buttonColor);
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setBounds(buttonStartingW, buttonStartingH, buttonWidth, buttonHeight);
        startButton.addActionListener(ear);
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        //how to button properties
        highScore.setText(highScoreString);
        highScore.setFont(buttonFont);
        highScore.setForeground(buttonColor);
        highScore.setBorder(BorderFactory.createEmptyBorder());
        highScore.setContentAreaFilled(false);
        highScore.setFocusPainted(false);
        highScore.setBounds(buttonStartingW, buttonStartingH + 50, buttonWidth, buttonHeight);
        highScore.addActionListener(ear);
        highScore.setHorizontalTextPosition(SwingConstants.CENTER);
        //options button properties
        optionsButton.setText(optionsString);
        optionsButton.setFont(buttonFont);
        optionsButton.setForeground(buttonColor);
        optionsButton.setBorder(BorderFactory.createEmptyBorder());
        optionsButton.setContentAreaFilled(false);
        optionsButton.setFocusPainted(false);
        optionsButton.setBounds(buttonStartingW, buttonStartingH + 100, buttonWidth, buttonHeight);
        optionsButton.addActionListener(ear);
        optionsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        //exit button properties
        exitButton.setText(exitString);
        exitButton.setFont(buttonFont);
        exitButton.setForeground(buttonColor);
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(buttonStartingW, buttonStartingH + 150, buttonWidth, buttonHeight);
        exitButton.addActionListener(ear);
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    public void initializeLabelProperties() {
        //header label properties
        headerLabel.setText(headerString);
        headerLabel.setFont(headerFont);
        headerLabel.setForeground(headerColor);
        headerLabel.setBounds(headerStartingW, headerStartingH, headerWidth, headerHeight);
        //copyright label properties
        copyrightLabel.setText(copyrightString);
        copyrightLabel.setFont(copyrightFont);
        copyrightLabel.setForeground(copyrightColor);
        copyrightLabel.setBounds(copyrightStartingW, copyrightStartingH, copyrightWidth, copyrightHeight);
    }

    public void initializeImagePanelProperties() {
        imagePanel.add(startButton);
        imagePanel.add(highScore);
        imagePanel.add(optionsButton);
        imagePanel.add(exitButton);
        imagePanel.add(headerLabel);
        imagePanel.add(copyrightLabel);
        imagePanel.setOpaque(true);
    }

    public void initializeFrameProperties() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                int n = JOptionPane.showConfirmDialog(
                        frame,
                        "Do you really want to exit ?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocation(180, 90);
        frame.setResizable(false);
        frame.setSize(new Dimension(imagePanel.getWidth(), imagePanel.getHeight()));
        frame.pack();
        frame.setVisible(true);
    }

    private class Ear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoadingScreen loadingScreen;
            if (e.getSource() == startButton) {
                loadingScreen = new LoadingScreen("Space Meteor", "Starting a new game...");
                try {
                    MainScreen.getInstance().frame.setVisible(false);
                    if (OptionsScreen.getInstance().frame.isVisible()) {
                        OptionsScreen.getInstance().frame.setVisible(false);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                MeteoricRain.getInstance();
                MeteoricRain.getInstance().frame.setVisible(true);
                loadingScreen.kill();
            } else if (e.getSource() == highScore) {
                JOptionPane.showMessageDialog(null, "Current High Score is : " + userPref.getInt("highScore", 0));
            } else if (e.getSource() == optionsButton) {
                loadingScreen = new LoadingScreen("Opening...","This may take a while depends on the number of fonts");
                OptionsScreen.getInstance();
                OptionsScreen.getInstance().frame.setVisible(true);
                loadingScreen.kill();
            } else {
                System.exit(0);
            }
        }
    }

}
