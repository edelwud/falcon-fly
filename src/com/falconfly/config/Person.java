package com.falconfly.config;

import javafx.beans.property.*;

public class Person{

    private SimpleStringProperty name;
    private SimpleIntegerProperty score;
    private SimpleIntegerProperty id;

    public Person(String name, int score, int id) {

        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String value) {
        name.set(value);
    }

    public int getScore() {
        return score.get();
    }
    public void setScore(int value){
        score.set(value);
    }

    public int getId() {
        return id.get();
    }
    public void setId(int value) {
        id.set(value);
    }
}
