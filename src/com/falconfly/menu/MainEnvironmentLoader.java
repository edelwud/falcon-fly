package com.falconfly.menu;

import com.falconfly.config.MainGlobals;
import javafx.util.Pair;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainEnvironmentLoader {

    private Scanner environmentFile;

    public void openEnvironment() {
        MenuStorageLoader loader = new MenuStorageLoader();
        try {
            environmentFile = new Scanner(new File(loader.Load("").get(0).substring(7,loader.Load("").get(0).length())));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Pair<String, String>> getScreenSizes() {

        List<Pair<String, String>> screenSizes = new LinkedList<>();
        this.openEnvironment();
        while(this.environmentFile.hasNext()) {
            screenSizes.add(new Pair<>(this.environmentFile.next(), this.environmentFile.next()));
        }
        this.environmentFile.close();
        return screenSizes;
    }

    public String getVolume() {

        String volume = new String();
        this.openEnvironment();
        while(this.environmentFile.hasNext()) {
            volume = this.environmentFile.next();
        }
        this.environmentFile.close();
        return volume;
    }
}
