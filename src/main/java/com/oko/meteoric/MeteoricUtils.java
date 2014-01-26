package com.oko.meteoric;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author oko
 */

public final class MeteoricUtils {

    private MeteoricUtils() {}

    private final static String TTF_EXTENSION =  ".ttf";
    private final static String WAV_EXTENSION =  ".wav";

    public static int findIndex(String[] array, String index) {
        int a = 0;
        for (String s : array) {
            if (s.equalsIgnoreCase(index)) {
                return a;
            }
            a++;
        }
        return -1;
    }

    public static String cutExtension(String str) {
        if (str == null) {
            return null;
        }
        int pos = str.lastIndexOf(".");
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /* ----- SPECIFIC TO APPLICATION ---- */

    public static String[] getColorNamesInEnvironment() {
        ArrayList<String> colors = new ArrayList<String>();
        for (Field f : Color.class.getFields()) {
            if (f.getType() == Color.class) {
                if (!colors.contains(f.getName().toLowerCase())) {
                    colors.add(f.getName());
                }
            }
        }
        return colors.toArray(new String[colors.size()]);
    }

    public static String[] getAvailableFontsWithCustomFonts(URL resourceUrl) {
        ArrayList<String> fontNames = new ArrayList<String>(Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
        try {
            File dir = new File(resourceUrl.toURI());
            for (File f : dir.listFiles()) {
                if (f.getName().toLowerCase().endsWith(TTF_EXTENSION)) {
                    fontNames.add(MeteoricUtils.cutExtension(f.getName()));
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return fontNames.toArray(new String[fontNames.size()]);
    }

    public static String[] getImagesInResources(URL resourceUrl) {
        ArrayList<String> images = new ArrayList<String>();
        try {
            File dir = new File(resourceUrl.toURI());
            for (File f : dir.listFiles()) {
                if ((!f.getName().toLowerCase().endsWith(TTF_EXTENSION)) && (!f.getName().toLowerCase().endsWith(WAV_EXTENSION))) {
                    images.add(f.getName());
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return images.toArray(new String[images.size()]);
    }

    public static Point[] getButtonPointSpace(JButton button) {
        Point p[] = new Point[65 * 81];
        int startW = button.getX();
        int startH = button.getY();
        for (int y = 0; y < 81; y++) {
            for (int x = 0; x < 65; x++) {
                p[(y * 65) + x] = new Point(x + startW, y + startH);
            }
        }
        return p;
    }



}
