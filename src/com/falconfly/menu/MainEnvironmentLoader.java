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
            environmentFile = new Scanner(new File("D:\\projects\\4_sem\\falcon-fly\\store\\environment.cfg"));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Pair<String, String>> getScreenSizes() {

        List<Pair<String, String>> screenSizes = new LinkedList<>();
        this.openEnvironment();
        String widthVar, heightVar;
        while(this.environmentFile.hasNext()) {
            //screenSizes.add(new Pair<>(this.environmentFile.next(), this.environmentFile.next()));
            widthVar = this.environmentFile.next();
            System.out.println(widthVar + "\n");
            heightVar = this.environmentFile.next();
        }
        this.environmentFile.close();
        return screenSizes;
    }
}
