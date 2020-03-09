package com.falconfly.menu;

import java.io.File;

public class MenuStorageLoader {

    private String storeRelativePath;

    public MenuStorageLoader() {
        this.storeRelativePath = new File("store\\").getAbsolutePath();
    }

    private String convertPath(String path) {
        return "file:///" + new File(path).getAbsolutePath().replace("\\", "/");
    }

    public String[] Load(String asset) {
        File[] files = new File(this.storeRelativePath  + "\\" + asset + "\\").listFiles();
        assert files != null;

        String[] relativePath = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            relativePath[i] = this.convertPath(files[i].getAbsolutePath());
        }
        return relativePath;
    }
}
