package com.falconfly.config;


import com.sun.tools.javac.Main;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Map;

public class MainFont {
   private Map<String, Font> fonts = new HashMap<String, Font>();
   public static MainFont instance;

   public MainFont() {
       instance = this;
   }
   public void addFont(String fontName, double size) {
       try {
           Font tempFont = new Font(fontName, size);
           fonts.put(fontName, tempFont);
       } catch (Exception e) {
            e.fillInStackTrace();
       }
   }
   public Font getFont(String fontName) {
       return fonts.get(fontName);
   }

   public static MainFont getInstance() {
       return instance;
   }
}
