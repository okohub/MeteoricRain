package com.oko.meteoric;

import java.io.IOException;

/**
 * @author oko
 */
public class Run {

    public static void main(String... args) throws IOException {
        LoadingScreen ls = new LoadingScreen("Loading Game...", "This may take a while depends on the size of image");
        MainScreen.getInstance();
        ls.kill();
    }

}

