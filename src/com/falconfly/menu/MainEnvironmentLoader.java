package com.falconfly.menu;

import com.falconfly.config.MainGlobals;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainEnvironmentLoader {

    private Scanner environmentFile;

    public void openEnvironment() {
        MenuStorageLoader loader = new MenuStorageLoader();
        try {
            environmentFile = new Scanner(loader.Load("").get(0));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Pair<String, String>> getScreenSizes() {

        List<Pair<String, String>> screenSizes = new LinkedList<>();
        while(this.environmentFile.hasNext()) {
            screenSizes.add(new Pair<>(this.environmentFile.next(),this.environmentFile.next()));
        }
        return screenSizes;
    }
}
