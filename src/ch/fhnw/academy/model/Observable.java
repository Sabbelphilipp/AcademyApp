package ch.fhnw.academy.model;

/**
 * Created by Reto Giger on 06.03.2015.
 */
public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
