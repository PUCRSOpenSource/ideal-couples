package com.company;

public class QueueElement {
    private Posicao position;
    private QueueElement parent;
    private int value;
    private int priority;

    public QueueElement(Posicao position, int value) {
        this.position = position;
        this.value = value;
    }

    public Posicao getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public QueueElement getParent() {
        return parent;
    }

    public void setParent(QueueElement parent) {
        this.parent = parent;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        QueueElement qe = (QueueElement) obj;
        return position.equals(qe.getPosition());
    }
}
