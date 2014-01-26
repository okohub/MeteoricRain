package com.oko.meteoric;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 * @author oko
 */

public class OptionsScreen {

    //singleton
    private static OptionsScreen instance = null;

    //pref
    Preferences userPref;

    //installed look and feels
    private LookAndFeelInfo looks[];

    //y - positions
    final int a1 = 35;
    final int a2 = 135;
    final int a3 = 235;
    final int a4 = 339;

    final int a5 = 472;
    final int a6 = 574;
    final int a7 = 662;

    File imageFile = null;
    File fontFile = null;

    //action
    final Ear ear = new Ear();

    //main frame
    final JFrame frame = new JFrame("Options");

    //labels
    final JLabel header1 = new JLabel("SETTINGS");

    final JLabel buttonsFontNameLabel = new JLabel("Buttons Font Name : ");
    final JLabel copyrightFontNameLabel = new JLabel("CopyRight Font Name : ");
    final JLabel headerFontNameLabel = new JLabel("Header Font Name : ");

    final JLabel buttonsFontSizeLabel = new JLabel("Buttons Font Size : ");
    final JLabel copyrightFontSizeLabel = new JLabel("CopyRight Font Size : ");
    final JLabel headerFontSizeLabel = new JLabel("Header Font Size : ");

    final JLabel buttonsFontStyleLabel = new JLabel("Buttons Font Style : ");
    final JLabel copyrightFontStyleLabel = new JLabel("CopyRight Font Style : ");
    final JLabel headerFontStyleLabel = new JLabel("Header Font Style : ");

    final JLabel lookAndFeelLabel = new JLabel("Look And Feel :");

    final JLabel buttonsPositionsLabel = new JLabel("Buttons Position : ");
    final JLabel copyrightPositionsLabel = new JLabel("CopyRight Position : ");
    final JLabel headerPositionsLabel = new JLabel("Header Position : ");

    final JLabel buttonsColorLabel = new JLabel("Buttons Color : ");
    final JLabel copyrightColorLabel = new JLabel("CopyRight Color : ");
    final JLabel headerColorLabel = new JLabel("Header Color : ");

    final JLabel backgroundImageLabel = new JLabel("Background Image :");

    final JLabel imageFileChooserLabel = new JLabel("Add Image File To Jar : ");
    final JLabel fontFileChooserLabel = new JLabel("Add Font File To Jar : ");

    //buttons
    final JButton saveButton = new JButton("Save");
    final JButton clearButton = new JButton("Clear");
    final JButton defaultButton = new JButton("Defaults");

    final JButton imageFileChooserButton = new JButton("...");
    final JButton fontFileChooserButton = new JButton("...");

    //Text Fields
    final JTextField buttonsPositions1 = new JTextField();
    final JTextField buttonsPositions2 = new JTextField();

    final JTextField copyrightPositions1 = new JTextField();
    final JTextField copyrightPositions2 = new JTextField();

    final JTextField headerPositions1 = new JTextField();
    final JTextField headerPositions2 = new JTextField();

    final JTextField buttonsFontSize = new JTextField();
    final JTextField copyrightFontSize = new JTextField();
    final JTextField headerFontSize = new JTextField();

    final JTextField imageFileChooserField = new JTextField();
    final JTextField fontFileChooserField = new JTextField();

    String[] fonts = MeteoricUtils.getAvailableFontsWithCustomFonts(this.getClass().getResource("/"));
    String[] colors = MeteoricUtils.getColorNamesInEnvironment();
    String[] images = MeteoricUtils.getImagesInResources(this.getClass().getResource("/"));
    String[] lookandfeelnames = getLookAndFeelInfo();

    //comboboxes
    final JComboBox<String> buttonsFontName = new JComboBox<String>(fonts);
    final JComboBox<String> copyrightFontName = new JComboBox<String>(fonts);
    final JComboBox<String> headerFontName = new JComboBox<String>(fonts);

    final JComboBox<String> lookAndFeelNames = new JComboBox<String>(lookandfeelnames);

    final JComboBox<String> buttonsFontColor = new JComboBox<String>(colors);
    final JComboBox<String> copyrightFontColor = new JComboBox<String>(colors);
    final JComboBox<String> headerFontColor = new JComboBox<String>(colors);

    final JComboBox<String> backgroundImage = new JComboBox<String>(images);

    //checkboxes
    final JCheckBox buttonsFontStyle1 = new JCheckBox("Bold");
    final JCheckBox buttonsFontStyle2 = new JCheckBox("Italic");
    final JCheckBox buttonsFontStyle3 = new JCheckBox("Plain");

    final JCheckBox copyrightFontStyle1 = new JCheckBox("Bold");
    final JCheckBox copyrightFontStyle2 = new JCheckBox("Italic");
    final JCheckBox copyrightFontStyle3 = new JCheckBox("Plain");

    final JCheckBox headerFontStyle1 = new JCheckBox("Bold");
    final JCheckBox headerFontStyle2 = new JCheckBox("Italic");
    final JCheckBox headerFontStyle3 = new JCheckBox("Plain");

    //fileChooser
    final JFileChooser imageFileChooser = new JFileChooser();
    final JFileChooser fontFileChooser = new JFileChooser();

    public OptionsScreen() {
        userPref = Preferences.userNodeForPackage(this.getClass());
        initializeLabels();
        initializeComboBoxes();
        initializeTextFields();
        initializeCheckBoxes();
        initializeButtons();
        initializeFrame();
    }

    public static OptionsScreen getInstance() {
        if (null == instance) {
            instance = new OptionsScreen();
        }
        return instance;
    }

    public void initializeLabels() {
        header1.setForeground(Color.BLUE);
        header1.setBounds(4, 0, 120, 20);

        /*************************************/

        buttonsFontNameLabel.setForeground(Color.red);
        buttonsFontNameLabel.setBounds(4, a1, 120, 10);

        headerFontNameLabel.setForeground(Color.red);
        headerFontNameLabel.setBounds(4, a1 + 30, 125, 15);

        copyrightFontNameLabel.setForeground(Color.red);
        copyrightFontNameLabel.setBounds(4, a1 + 60, 130, 15);

        /*********************************/

        buttonsFontSizeLabel.setForeground(Color.red);
        buttonsFontSizeLabel.setBounds(4, a2, 120, 15);

        headerFontSizeLabel.setForeground(Color.red);
        headerFontSizeLabel.setBounds(4, a2 + 30, 120, 15);

        copyrightFontSizeLabel.setForeground(Color.red);
        copyrightFontSizeLabel.setBounds(4, a2 + 60, 120, 15);

        /*****************************************/

        buttonsFontStyleLabel.setForeground(Color.red);
        buttonsFontStyleLabel.setBounds(4, a3, 120, 15);

        headerFontStyleLabel.setForeground(Color.red);
        headerFontStyleLabel.setBounds(4, a3 + 30, 120, 15);

        copyrightFontStyleLabel.setForeground(Color.red);
        copyrightFontStyleLabel.setBounds(4, a3 + 60, 125, 15);

        /*************************************************/

        lookAndFeelLabel.setForeground(Color.red);
        lookAndFeelLabel.setBounds(4, a4, 130, 15);

        buttonsPositionsLabel.setForeground(Color.red);
        buttonsPositionsLabel.setBounds(4, a4 + 30, 120, 15);

        headerPositionsLabel.setForeground(Color.red);
        headerPositionsLabel.setBounds(4, a4 + 60, 120, 15);

        copyrightPositionsLabel.setForeground(Color.red);
        copyrightPositionsLabel.setBounds(4, a4 + 90, 125, 15);

        /***************************************************/

        buttonsColorLabel.setForeground(Color.red);
        buttonsColorLabel.setBounds(4, a5, 120, 15);

        headerColorLabel.setForeground(Color.red);
        headerColorLabel.setBounds(4, a5 + 30, 120, 15);

        copyrightColorLabel.setForeground(Color.red);
        copyrightColorLabel.setBounds(4, a5 + 60, 125, 15);

        /****************************************************************/

        backgroundImageLabel.setForeground(Color.red);
        backgroundImageLabel.setBounds(4, a6, 120, 15);

        imageFileChooserLabel.setForeground(Color.red);
        imageFileChooserLabel.setBounds(4, a6 + 30, 130, 15);

        fontFileChooserLabel.setForeground(Color.red);
        fontFileChooserLabel.setBounds(4, a6 + 60, 120, 15);

        /************************************************************/
    }

    public void initializeComboBoxes() {

        buttonsFontName.setBounds(135, a1 - 4, 120, 20);
        headerFontName.setBounds(135, a1 + 26, 120, 20);
        copyrightFontName.setBounds(135, a1 + 56, 120, 20);

        lookAndFeelNames.setBounds(135, a4, 120, 20);
        lookAndFeelNames.addActionListener(ear);

        buttonsFontColor.setBounds(135, a5 - 4, 120, 20);
        headerFontColor.setBounds(135, a5 + 26, 120, 20);
        copyrightFontColor.setBounds(135, a5 + 56, 120, 20);

        backgroundImage.setBounds(135, a6 - 4, 120, 20);

    }

    public void initializeTextFields() {

        buttonsFontSize.setBounds(135, a2 - 2, 30, 20);
        headerFontSize.setBounds(135, a2 + 28, 30, 20);
        copyrightFontSize.setBounds(135, a2 + 58, 30, 20);

        buttonsPositions1.setBounds(135, a4 + 28, 30, 20);
        buttonsPositions2.setBounds(170, a4 + 28, 30, 20);

        headerPositions1.setBounds(135, a4 + 58, 30, 20);
        headerPositions2.setBounds(170, a4 + 58, 30, 20);

        copyrightPositions1.setBounds(135, a4 + 88, 30, 20);
        copyrightPositions2.setBounds(170, a4 + 88, 30, 20);

        imageFileChooserField.setEditable(false);
        imageFileChooserField.setBounds(135, a6 + 26, 101, 20);
        fontFileChooserField.setEditable(false);
        fontFileChooserField.setBounds(135, a6 + 56, 101, 20);


    }

    public void initializeCheckBoxes() {

        buttonsFontStyle1.setBounds(132, a3 - 5, 50, 25);
        buttonsFontStyle1.addItemListener(ear);

        buttonsFontStyle2.setBounds(183, a3 - 5, 55, 25);

        buttonsFontStyle3.setBounds(234, a3 - 5, 55, 25);
        buttonsFontStyle3.addItemListener(ear);

        headerFontStyle1.setBounds(132, a3 + 25, 50, 25);
        headerFontStyle1.addItemListener(ear);

        headerFontStyle2.setBounds(183, a3 + 25, 55, 25);

        headerFontStyle3.setBounds(234, a3 + 25, 55, 25);
        headerFontStyle3.addItemListener(ear);

        copyrightFontStyle1.setBounds(132, a3 + 55, 50, 25);
        copyrightFontStyle1.addItemListener(ear);

        copyrightFontStyle2.setBounds(183, a3 + 55, 55, 25);

        copyrightFontStyle3.setBounds(234, a3 + 55, 55, 25);
        copyrightFontStyle3.addItemListener(ear);

    }

    public void initializeButtons() {
        saveButton.setBounds(4, a7, 90, 25);
        clearButton.setBounds(100, a7, 90, 25);
        defaultButton.setBounds(196, a7, 90, 25);
        imageFileChooserButton.setBounds(246, a6 + 26, 40, 20);
        fontFileChooserButton.setBounds(246, a6 + 56, 40, 20);
        saveButton.addActionListener(ear);
        clearButton.addActionListener(ear);
        defaultButton.addActionListener(ear);
        imageFileChooserButton.addActionListener(ear);
        fontFileChooserButton.addActionListener(ear);
    }

    public void initializeFrame() {

        Dimension size = new Dimension(296, 720);
        frame.setPreferredSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
        frame.setSize(size);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.add(header1);
        /**************************************/
        frame.add(buttonsFontNameLabel);
        frame.add(buttonsFontName);

        frame.add(copyrightFontNameLabel);
        frame.add(copyrightFontName);

        frame.add(headerFontNameLabel);
        frame.add(headerFontName);
        /******************************************/
        frame.add(buttonsFontSizeLabel);
        frame.add(buttonsFontSize);

        frame.add(headerFontSizeLabel);
        frame.add(headerFontSize);

        frame.add(copyrightFontSizeLabel);
        frame.add(copyrightFontSize);
        /***************************************************/
        frame.add(buttonsFontStyleLabel);
        frame.add(buttonsFontStyle1);
        frame.add(buttonsFontStyle2);
        frame.add(buttonsFontStyle3);

        frame.add(headerFontStyleLabel);
        frame.add(headerFontStyle1);
        frame.add(headerFontStyle2);
        frame.add(headerFontStyle3);

        frame.add(copyrightFontStyleLabel);
        frame.add(copyrightFontStyle1);
        frame.add(copyrightFontStyle2);
        frame.add(copyrightFontStyle3);
        /**************************************************/
        frame.add(lookAndFeelLabel);
        frame.add(lookAndFeelNames);

        frame.add(buttonsPositionsLabel);
        frame.add(buttonsPositions1);
        frame.add(buttonsPositions2);


        frame.add(headerPositionsLabel);
        frame.add(headerPositions1);
        frame.add(headerPositions2);

        frame.add(copyrightPositionsLabel);
        frame.add(copyrightPositions1);
        frame.add(copyrightPositions2);
        /****************************************************/
        frame.add(buttonsColorLabel);
        frame.add(buttonsFontColor);

        frame.add(headerColorLabel);
        frame.add(headerFontColor);

        frame.add(copyrightColorLabel);
        frame.add(copyrightFontColor);
        /********************************************************/
        frame.add(backgroundImageLabel);
        frame.add(backgroundImage);

        frame.add(imageFileChooserLabel);
        frame.add(imageFileChooserField);
        frame.add(imageFileChooserButton);

        frame.add(fontFileChooserLabel);
        frame.add(fontFileChooserField);
        frame.add(fontFileChooserButton);
        /*****************************************************/
        frame.add(saveButton);
        frame.add(clearButton);
        frame.add(defaultButton);

        frame.setLocation(880, 10);
        frame.pack();
        frame.setResizable(false);
        setPreferences();
        changeTheLookAndFeel(userPref.get("lookAndFeelName", "Metal"));
        frame.setVisible(true);
    }

    public void clearFields() {

        buttonsFontName.setSelectedItem(null);
        headerFontName.setSelectedItem(null);
        copyrightFontName.setSelectedItem(null);
        lookAndFeelNames.setSelectedIndex(MeteoricUtils.findIndex(lookandfeelnames, "Metal"));

        buttonsFontSize.setText(null);
        headerFontSize.setText(null);
        copyrightFontSize.setText(null);

        buttonsFontStyle1.setSelected(false);
        buttonsFontStyle2.setSelected(false);
        buttonsFontStyle3.setSelected(false);

        headerFontStyle1.setSelected(false);
        headerFontStyle2.setSelected(false);
        headerFontStyle3.setSelected(false);

        copyrightFontStyle1.setSelected(false);
        copyrightFontStyle2.setSelected(false);
        copyrightFontStyle3.setSelected(false);

        buttonsPositions1.setText(null);
        buttonsPositions2.setText(null);

        headerPositions1.setText(null);
        headerPositions2.setText(null);

        copyrightPositions1.setText(null);
        copyrightPositions2.setText(null);

        buttonsFontColor.setSelectedItem(null);
        headerFontColor.setSelectedItem(null);
        copyrightFontColor.setSelectedItem(null);

        backgroundImage.setSelectedItem(null);

        imageFileChooserField.setText(null);
        imageFile = null;

        fontFileChooserField.setText(null);
        fontFile = null;


    }

    public void setDefaults() {

        buttonsFontName.setSelectedIndex(MeteoricUtils.findIndex(fonts, "VTKSAnimal2"));
        headerFontName.setSelectedIndex(MeteoricUtils.findIndex(fonts, "ICBMSS25"));
        copyrightFontName.setSelectedIndex(MeteoricUtils.findIndex(fonts, "ICBMSS25"));

        buttonsFontSize.setText("35");
        headerFontSize.setText("55");
        copyrightFontSize.setText("10");

        buttonsFontStyle1.setSelected(true);
        buttonsFontStyle2.setSelected(false);
        buttonsFontStyle3.setSelected(false);

        headerFontStyle1.setSelected(true);
        headerFontStyle2.setSelected(false);
        headerFontStyle3.setSelected(false);

        copyrightFontStyle1.setSelected(true);
        copyrightFontStyle2.setSelected(false);
        copyrightFontStyle3.setSelected(false);

        lookAndFeelNames.setSelectedIndex(MeteoricUtils.findIndex(lookandfeelnames, "Metal"));

        buttonsPositions1.setText("107");
        buttonsPositions2.setText("350");

        headerPositions1.setText("251");
        headerPositions2.setText("2");

        copyrightPositions1.setText("755");
        copyrightPositions2.setText("555");

        buttonsFontColor.setSelectedIndex(MeteoricUtils.findIndex(colors, "orange"));
        headerFontColor.setSelectedIndex(MeteoricUtils.findIndex(colors, "orange"));
        copyrightFontColor.setSelectedIndex(MeteoricUtils.findIndex(colors, "orange"));

        backgroundImage.setSelectedIndex(MeteoricUtils.findIndex(images, "MainBackground.jpg"));
    }

    public void save() {
        userPref = Preferences.userNodeForPackage(this.getClass());
        try {
            userPref.putInt("copyrightFontSize", Integer.parseInt(copyrightFontSize.getText()));
            userPref.putInt("buttonFontSize", Integer.parseInt(buttonsFontSize.getText()));
            userPref.putInt("headerFontSize", Integer.parseInt(headerFontSize.getText()));
            userPref.putInt("buttonStartingW", Integer.parseInt(buttonsPositions1.getText()));
            userPref.putInt("buttonStartingH", Integer.parseInt(buttonsPositions2.getText()));
            userPref.putInt("headerStartingW", Integer.parseInt(headerPositions1.getText()));
            userPref.putInt("headerStartingH", Integer.parseInt(headerPositions2.getText()));
            userPref.putInt("copyrightStartingW", Integer.parseInt(copyrightPositions1.getText()));
            userPref.putInt("copyrightStartingH", Integer.parseInt(copyrightPositions2.getText()));
            userPref.put("copyrightColor", copyrightFontColor.getItemAt(copyrightFontColor.getSelectedIndex()));
            userPref.put("buttonColor", buttonsFontColor.getItemAt(buttonsFontColor.getSelectedIndex()));
            userPref.put("headerColor", headerFontColor.getItemAt(headerFontColor.getSelectedIndex()));
            userPref.put("copyrightFontName", copyrightFontName.getItemAt(copyrightFontName.getSelectedIndex()) + ".ttf");
            userPref.put("buttonFontName", buttonsFontName.getItemAt(buttonsFontName.getSelectedIndex()) + ".ttf");
            userPref.put("headerFontName", headerFontName.getItemAt(headerFontName.getSelectedIndex()) + ".ttf");
            int style = findStyleConstant(buttonsFontStyle1, buttonsFontStyle2, buttonsFontStyle3);
            userPref.putInt("buttonFontStyle", style);
            style = findStyleConstant(headerFontStyle1, headerFontStyle2, headerFontStyle3);
            userPref.putInt("headerFontStyle", style);
            style = findStyleConstant(copyrightFontStyle1, copyrightFontStyle2, copyrightFontStyle3);
            userPref.putInt("copyrightFontStyle", style);
            userPref.put("background", backgroundImage.getItemAt(backgroundImage.getSelectedIndex()));
            userPref.put("lookAndFeelName", lookAndFeelNames.getItemAt(lookAndFeelNames.getSelectedIndex()));
            JOptionPane.showMessageDialog(null, "Preferences Saved !", "Saving Preferences...", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please fill all of them");
        }
    }

    public void setPreferences() {
        buttonsFontName.setSelectedIndex(MeteoricUtils.findIndex(fonts, MeteoricUtils.cutExtension(userPref.get("buttonFontName", "VTKSAnimal2.ttf"))));
        headerFontName.setSelectedIndex(MeteoricUtils.findIndex(fonts, MeteoricUtils.cutExtension(userPref.get("headerFontName", "ICBMSS25.ttf"))));
        copyrightFontName.setSelectedIndex(MeteoricUtils.findIndex(fonts, MeteoricUtils.cutExtension(userPref.get("copyrightFontName", "ICBMSS25.ttf"))));

        buttonsFontSize.setText(String.valueOf(userPref.getInt("buttonFontSize", 35)));
        headerFontSize.setText(String.valueOf(userPref.getInt("headerFontSize", 55)));
        copyrightFontSize.setText(String.valueOf(userPref.getInt("copyrightFontSize", 10)));

        int style = userPref.getInt("buttonFontStyle", 1);
        findStyleSelected(style, buttonsFontStyle1, buttonsFontStyle2, buttonsFontStyle3);
        style = userPref.getInt("headerFontStyle", 1);
        findStyleSelected(style, headerFontStyle1, headerFontStyle2, headerFontStyle3);
        style = userPref.getInt("copyrightFontStyle", 1);
        findStyleSelected(style, copyrightFontStyle1, copyrightFontStyle2, copyrightFontStyle3);

        lookAndFeelNames.setSelectedIndex(MeteoricUtils.findIndex(lookandfeelnames, (userPref.get("lookAndFeelName", "Metal"))));

        buttonsPositions1.setText(String.valueOf(userPref.getInt("buttonStartingW", 107)));
        buttonsPositions2.setText(String.valueOf(userPref.getInt("buttonStartingH", 350)));

        headerPositions1.setText(String.valueOf(userPref.getInt("headerStartingW", 251)));
        headerPositions2.setText(String.valueOf(userPref.getInt("headerStartingH", 2)));

        copyrightPositions1.setText(String.valueOf(userPref.getInt("copyrightStartingW", 755)));
        copyrightPositions2.setText(String.valueOf(userPref.getInt("copyrightStartingH", 555)));

        buttonsFontColor.setSelectedIndex(MeteoricUtils.findIndex(colors, userPref.get("buttonColor", "orange")));
        headerFontColor.setSelectedIndex(MeteoricUtils.findIndex(colors, userPref.get("headerColor", "orange")));
        copyrightFontColor.setSelectedIndex(MeteoricUtils.findIndex(colors, userPref.get("copyrightColor", "orange")));

        backgroundImage.setSelectedIndex(MeteoricUtils.findIndex(images, userPref.get("background", "MainBackground.jpg")));
    }



    private static int findStyleConstant(JCheckBox c1, JCheckBox c2, JCheckBox c3) {
        if (c1.isSelected() && c2.isSelected()) {
            return 3;
        } else if (c2.isSelected() && c3.isSelected()) {
            return 2;
        } else if (c1.isSelected()) {
            return 1;
        }
        return 0;
    }

    private static void findStyleSelected(int style, JCheckBox c1, JCheckBox c2, JCheckBox c3) {
        switch (style) {
            case 0:
                c1.setSelected(false);
                c2.setSelected(false);
                c3.setSelected(true);
                break;
            case 1:
                c1.setSelected(true);
                c2.setSelected(false);
                c3.setSelected(false);
                break;
            case 2:
                c1.setSelected(false);
                c2.setSelected(true);
                c3.setSelected(true);
                break;
            case 3:
                c1.setSelected(true);
                c2.setSelected(true);
                c3.setSelected(false);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Something gone wrong :/", "Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void changeTheLookAndFeel(String name) {
        try {
            int i = MeteoricUtils.findIndex(lookandfeelnames, name);
            UIManager.setLookAndFeel(looks[i].getClassName());
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getLookAndFeelInfo() {
        ArrayList<String> names = new ArrayList<String>();
        looks = UIManager.getInstalledLookAndFeels();
        for (LookAndFeelInfo info : looks) {
            names.add(info.getName());
        }
        return names.toArray(new String[names.size()]);
    }

    private class Ear implements ActionListener, ItemListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == saveButton) {
                save();
            }
            if (e.getSource() == clearButton) {
                clearFields();
            }
            if (e.getSource() == defaultButton) {
                setDefaults();
            }
            if (e.getSource() == imageFileChooserButton) {
                FileFilter filter = new FileNameExtensionFilter("Image File", "png", "jpg", "jpeg", "bmp", "gif");
                imageFileChooser.addChoosableFileFilter(filter);
                imageFileChooser.setAcceptAllFileFilterUsed(false);
                imageFileChooser.setFileFilter(filter);

                int returnVal = imageFileChooser.showOpenDialog(null);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    imageFile = imageFileChooser.getSelectedFile();
                    imageFileChooserField.setText(imageFileChooser.getSelectedFile().getName());
                } else {
                    imageFileChooserField.setText(null);
                    imageFile = null;
                }
            }
            if (e.getSource() == fontFileChooserButton) {
                FileFilter filter = new FileNameExtensionFilter("TrueType Font", "ttf");
                fontFileChooser.addChoosableFileFilter(filter);
                fontFileChooser.setAcceptAllFileFilterUsed(false);
                fontFileChooser.setFileFilter(filter);
                int returnVal = fontFileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    fontFile = fontFileChooser.getSelectedFile();
                    fontFileChooserField.setText(fontFileChooser.getSelectedFile().getName());
                } else {
                    fontFileChooserField.setText(null);
                    fontFile = null;
                }
            }
            if (e.getSource() == lookAndFeelNames) {
                changeTheLookAndFeel((String) lookAndFeelNames.getSelectedItem());
            }
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getItemSelectable() == buttonsFontStyle1) {
                buttonsFontStyle3.setSelected(!(buttonsFontStyle1.isSelected()));
            }
            if (e.getItemSelectable() == buttonsFontStyle3) {
                buttonsFontStyle1.setSelected(!(buttonsFontStyle3.isSelected()));
            }
            if (e.getItemSelectable() == headerFontStyle1) {
                headerFontStyle3.setSelected(!(headerFontStyle1.isSelected()));
            }
            if (e.getItemSelectable() == headerFontStyle3) {
                headerFontStyle1.setSelected(!(headerFontStyle3.isSelected()));
            }
            if (e.getItemSelectable() == copyrightFontStyle1) {
                copyrightFontStyle3.setSelected(!(copyrightFontStyle1.isSelected()));
            }
            if (e.getItemSelectable() == copyrightFontStyle3) {
                copyrightFontStyle1.setSelected(!(copyrightFontStyle3.isSelected()));
            }
        }
    }


}

