package com.falconfly.menu;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MenuStorageLoader {

    private String storeRelativePath;

    public MenuStorageLoader() {
        this.storeRelativePath = new File("store\\").getAbsolutePath();
    }

    private String convertPath(String path) {
        return "file:///" + new File(path).getAbsolutePath().replace("\\", "/");
    }

    public List<String> Load(String asset) {
        File[] files = new File(this.storeRelativePath  + "\\" + asset + "\\").listFiles();
        assert files != null;

        List<String> relativePath = new LinkedList<>();
        for (File file:files) {
            relativePath.add(this.convertPath(file.getAbsolutePath()));
        }
        return relativePath;
    }
}
