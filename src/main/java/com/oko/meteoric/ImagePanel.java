package com.oko.meteoric;


import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import javax.imageio.ImageIO;

/**
 * @author oko
 */

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    final static int MAX = 1000;
    final static int DEFAULT_WIDTH = 900;
    final static int DEFAULT_HEIGHT = 500;

    private Image img;

    public ImagePanel(String img) throws IOException {
        this(new File(img));
    }

    public ImagePanel(File f) throws IOException {
        this(f.toURI().toURL());
    }

    public ImagePanel(URL u) throws IOException {
        this(ImageIO.read(u));
    }

    public ImagePanel(Image img) {

        Image i = img;
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        // minimize to default image values
        if (w > MAX || h > MAX) {
            while (w > MAX || h > MAX) {
                i = scale(0.9f, 0.9f, i);
                w = i.getWidth(null);
                h = i.getHeight(null);
            }
        }
        //enlarge to default image values
        else if (w < DEFAULT_WIDTH && h < DEFAULT_HEIGHT) {
            while ((w < DEFAULT_WIDTH && h < DEFAULT_HEIGHT)) {
                i = scale(1.1f, 1.1f, i);
                w = i.getWidth(null);
                h = i.getHeight(null);
            }
        }
        // control height default
        if (h < DEFAULT_HEIGHT) {
            while (h < DEFAULT_HEIGHT) {
                i = scale(1f, 1.1f, i);
                w = i.getWidth(null);
                h = i.getHeight(null);
            }
        }
        // control width default
        if (w < DEFAULT_WIDTH) {
            while (w < DEFAULT_WIDTH) {
                i = scale(1.1f, 1f, i);
                w = i.getWidth(null);
            }
        }
        this.img = i;
        Dimension size = new Dimension(this.img.getWidth(null), this.img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    private static Image scale(float f, float g, Image image) {
        image = image.getScaledInstance(
                (int) (f * image.getWidth(null)),
                (int) (g * image.getHeight(null)),
                Image.SCALE_SMOOTH);
        return image;
    }

}
