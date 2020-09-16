package ru.dpankratov.projects.takeoverplanet.Client;

public class ClientAction {
    private int from;
    private int to;
    private int count;

    public ClientAction(int from, int to, int count){
        this.from = from;
        this.to = to;
        this.count = count;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getCount(){return this.count; }
    public void setCount(int count){ this.count = count; }
}
