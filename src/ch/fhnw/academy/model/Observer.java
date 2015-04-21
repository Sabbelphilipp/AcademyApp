package ch.fhnw.academy.model;

/**
 * Created by Reto Giger on 06.03.2015.
 */
public interface Observer {
    void update(Observable model);
    void addNewRow(int index);
    void removeRow(int index);
}
