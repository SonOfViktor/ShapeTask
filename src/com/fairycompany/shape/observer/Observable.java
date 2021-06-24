package com.fairycompany.shape.observer;

public interface Observable {
    void attach(Observer observer);
    void detach();
    void notifyObservers();
}
