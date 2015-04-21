package ch.fhnw.academy.gui;

import ch.fhnw.academy.model.MovieList;

/**
 * Created by Reto Giger on 10.03.2015.
 */
public class testMain {

    public static void main(String[] args) {
        MovieList ml= new MovieList();

        System.out.println(ml.getSelectedMovie());

    }
}
