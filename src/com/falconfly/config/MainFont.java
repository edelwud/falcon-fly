package com.falconfly.config;


import com.falconfly.menu.MainEnvironmentLoader;
import com.falconfly.menu.MainMenu;
import com.falconfly.menu.MenuStorageLoader;
import com.sun.tools.javac.Main;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MainFont {

   MenuStorageLoader loader;

   private Map<String, Font> fonts = new HashMap<String, Font>();
   public static MainFont instance;

   public MainFont() {
       instance = this;
   }
   public void addFont(String fontName, double size) throws IOException {
       try {
           Font tempFont = new Font(fontName, size);
           fonts.put(fontName, tempFont);
       } catch (Exception ex) {
           MainLogger logger = new MainLogger( loader.Load("").get(3).substring(8) + "/LogMainFont.txt", MainMenu.class.getSimpleName());
           logger.logger.info(ex.toString());
       }
   }
   public Font getFont(String fontName) {
       return fonts.get(fontName);
   }

   public static MainFont getInstance() {
       return instance;
   }
}
