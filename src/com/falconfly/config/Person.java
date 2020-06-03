package com.falconfly.config;

import javafx.beans.property.*;

public class Person{

    private SimpleStringProperty name;
    private SimpleLongProperty score;
    private SimpleIntegerProperty id;

    public Person(String name, long score, int id) {

        this.name = new SimpleStringProperty(name);
        this.score = new SimpleLongProperty(score);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String value) {
        name.set(value);
    }

    public long getScore() {
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
