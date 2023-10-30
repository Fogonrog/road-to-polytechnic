package com.road.polytechnic.model;
public class Event {
    private int name;
    private int phrase;
    private int person;
    private int background;

    public Event(int name, int phrase, int person, int background) {
        this.name = name;
        this.phrase = phrase;
        this.person = person;
        this.background = background;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setPhrase(int phrase) {
        this.phrase = phrase;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getName() {
        return name;
    }

    public int getPhrase() {
        return phrase;
    }

    public int getPerson() {
        return person;
    }

    public int getBackground() {
        return background;
    }
}
